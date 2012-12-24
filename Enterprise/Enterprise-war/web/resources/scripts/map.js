var map;					// карта
var markersLayer;			// маркеры
var geometryLayer;			// маркеры
var mapnik; 				// подложка OSM
var fromProjection;			
var toProjectio;   
var position;      
var zoom;          
var renderer;
var vectorLayer; 





OpenLayers.Control.Click = OpenLayers.Class(OpenLayers.Control, {                
    defaultHandlerOptions: {
        'single': true,
        'double': false,
        'pixelTolerance': 0,
        'stopSingle': false,
        'stopDouble': false
    },
    handleRightClicks:true,
    initialize: function(options) {
        this.handlerOptions = OpenLayers.Util.extend(
        {}, this.defaultHandlerOptions
            );
        OpenLayers.Control.prototype.initialize.apply(
            this, arguments
            ); 
        this.handler = new OpenLayers.Handler.Click(
            this, {
                'click': this.clickTrigger,
                'rightclick': this.rightTrigger
            }, this.handlerOptions
            );
    }, 

    clickTrigger: function(e) {
        var lonlat = map.getLonLatFromViewPortPx(e.xy);
        e.lon = lonlat.transform(toProjection, fromProjection).lon;
        e.lat = lonlat.lat;
        document.getElementById('basicMap').llon = e.lon;
        document.getElementById('basicMap').llat = e.lat;
    },
    rightTrigger: function(e) {
        var lonlat = map.getLonLatFromViewPortPx(e.xy);
        e.lon = lonlat.transform(toProjection, fromProjection).lon;
        e.lat = lonlat.lat;
        document.getElementById('basicMap').rlon = e.lon;
        document.getElementById('basicMap').rlat = e.lat;
    }

});

function init(focusLon,focusLat,zoomInit) {
    document.getElementById('basicMap').oncontextmenu = function(e){
        e = e?e:window.event;
        if (e.preventDefault) e.preventDefault(); // For non-IE browsers.
        else return false; // For IE browsers.
    };


    map 			= 		new OpenLayers.Map({
        div:"basicMap",
        resolutions:null,
        controls: [
        new OpenLayers.Control.Navigation(
        {
            dragPanOptions: {
                enableKinetic: true
            }
        }
        ),
        new OpenLayers.Control.PanZoomBar(),
        new OpenLayers.Control.LayerSwitcher({
            'ascending':false
        }),
        new OpenLayers.Control.Permalink(),
        new OpenLayers.Control.ScaleLine(),
        new OpenLayers.Control.Permalink('permalink'),
        new OpenLayers.Control.MousePosition({
            displayProjection:new OpenLayers.Projection("EPSG:4326")
        }), //WGS 1984
        new OpenLayers.Control.OverviewMap(),
        new OpenLayers.Control.KeyboardDefaults()
        ]
    });	
	           
    mapnik         	= 		new OpenLayers.Layer.OSM();
    fromProjection 	= 		new OpenLayers.Projection("EPSG:4326");   // Transform from WGS 1984
    toProjection   	= 		new OpenLayers.Projection("EPSG:900913"); // to Spherical Mercator Projection
    position       	= 		new OpenLayers.LonLat(focusLon,focusLat).transform( fromProjection, toProjection);
    zoom           	= 		zoomInit; 
    renderer = OpenLayers.Util.getParameters(window.location.href).renderer;
    renderer = (renderer) ? [renderer] : OpenLayers.Layer.Vector.prototype.renderers;

    markersLayer = new OpenLayers.Layer.Markers( "Markers");
	
	
    vectorLayer = new OpenLayers.Layer.Vector("Marker labels", {
        styleMap: new OpenLayers.StyleMap({
            'default':{
                strokeColor: "#FF0000",
                strokeOpacity: 1,
                strokeWidth: 3,
                fillColor: "#FF5500",
                fillOpacity: 0.5,
                pointRadius: 0,
                pointerEvents: "visiblePainted",
                // label with \n linebreaks
                label : "${name}",				
                fontColor: "#333333",
                fontSize: "16px",
                fontFamily: "Arial Black, monospace",
                fontWeight: "bold",
                //labelAlign: "${align}",
                //labelXOffset: "${xOffset}",
                labelYOffset: "-15",
                labelOutlineColor: "white",
                labelOutlineWidth: 3
            }
        }),
        renderers: renderer
    });
	
    geometryLayer = vectorLayer.clone();
    geometryLayer.name = "Geometry";
	
    directionLayer = geometryLayer.clone();
    directionLayer.styleMap.name = null;

    map.addLayer(markersLayer);
    map.addLayer(geometryLayer);
    map.addLayer(vectorLayer);
    map.addLayer(directionLayer);
    map.addLayer(mapnik);

    var click = new OpenLayers.Control.Click();
    map.addControl(click);
    click.activate();
    map.setCenter(position, zoom);		
}

function addMarker(lon, lat, arrow, label, attrs) {	
    // create a point feature
	
    point = new OpenLayers.Geometry.Point(lon, lat).transform( fromProjection, toProjection);
    var pointFeature = new OpenLayers.Feature.Vector(point,null);
    pointFeature.attributes = {
        type: "marker",
        name: label,
        description: attrs.description,			
        stateNumber: attrs.stateNumber,
        picture: attrs.picture,			
        icon : new OpenLayers.Icon(arrow),  
        favColor: 'black',
        align: "cm"
    };	
		
	
    size = new OpenLayers.Size(25,25);
    offset = new OpenLayers.Pixel(-(size.w/2), -size.h);
    icon = new OpenLayers.Icon(arrow,size,offset);        
    var marker = new OpenLayers.Marker(new OpenLayers.LonLat(lon, lat).transform( fromProjection, toProjection),icon );
				


				
    marker.events.register("mousedown", marker, function(e) {
        if (this.popup == null) {							
            this.popup = new OpenLayers.Popup.FramedCloud( "dragging-issue", 
                marker.lonlat,
                null,
                '\
															  <div style="font-size:.8em"><b>Name:</b> ' + pointFeature.attributes.name+'<br>\
															  <b>State number:</b> ' + pointFeature.attributes.stateNumber+'<br>\
															  <b>Description:</b> ' + pointFeature.attributes.description+'</div>\
															  <b>Photo: </b><br> <img src="'+pointFeature.attributes.picture+'" style="image-orientation: 40deg;" width="200px" heigth="200px" />',
                null, true);
            map.addPopup(this.popup);
										
        }	
        else {
            this.popup.toggle();
        }	
    });				
		
    pointFeature.marker=marker;
	
    vectorLayer.addFeatures([pointFeature]);
    markersLayer.addMarker(marker);
}

function centerAt(lon, lat) {
    map.setCenter(new OpenLayers.LonLat(lon,lat).transform( fromProjection, toProjection));
}
