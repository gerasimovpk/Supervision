var WGS84 = new OpenLayers.Projection("EPSG:4326"); // Transform from WGS 1984;
var WGS84Google = new OpenLayers.Projection("EPSG:900913"); // to Spherical
// Mercator
// Projection;
var RSUBDMap;
var objfilter;
var objfilterStrategy;
var uofilter;
var uofilterStrategy;
var editableLayer;
var routefilter;
var routefilterStrategy;
var objects;

function ResizeMap(lon, lat) {
    RSUBDMap.zoomTo(12);
    RSUBDMap.setCenter(new OpenLayers.LonLat(lon, lat).transform(WGS84,
        WGS84Google));
}

function CenterMap(lon, lat) {
    RSUBDMap.setCenter(new OpenLayers.LonLat(lon, lat).transform(WGS84,
        WGS84Google));
}

function init() {
    OpenLayers.ProxyHost = "../../proxy.jsp?";
    
    // set up a save strategy
    var saveStrategy = new OpenLayers.Strategy.Save();

    renderer = OpenLayers.Util.getParameters(window.location.href).renderer;
    renderer = (renderer) ? [ renderer ]
    : OpenLayers.Layer.Vector.prototype.renderers;

    // set up projections
    // World Geodetic System 1984 projection
    var WGS84 = new OpenLayers.Projection("EPSG:4326");
    // WGS84 Google Mercator projection
    var WGS84_google_mercator = new OpenLayers.Projection("EPSG:900913");

    // Initialize the RSUBDMap
    RSUBDMap = new OpenLayers.Map("basicMap", {
        controls : [ new OpenLayers.Control.Navigation(),
        new OpenLayers.Control.PanZoomBar(),
        new OpenLayers.Control.ScaleLine(),
        new OpenLayers.Control.DragFeature(),
        new OpenLayers.Control.LayerSwitcher(),
        new OpenLayers.Control.OverviewMap(),
        new OpenLayers.Control.MousePosition() ],
        projection : WGS84,
        displayProjection : WGS84
    });

    // map extent
    var mapextent = new OpenLayers.Bounds(34.17341, 57.24343, 47.06183,
        52.29899).transform(WGS84, WGS84_google_mercator);

    // base layers
    var openstreetmap = new OpenLayers.Layer.OSM();   


    objects = new OpenLayers.Layer.Vector("Vehicles", {
        strategies : [ new OpenLayers.Strategy.BBOX() ],
        protocol : new OpenLayers.Protocol.WFS({
            url : "http://localhost:8080/geoserver/wfs",
            featureType : "CTD",
            featurePrefix : "rsubd",
            featureNS : "http://isim.vlsu.ru/rsubd",
            srsName : "EPSG:4326",
            geometryName : "THE_GEOM",
            version : "1.1.0"
        }),
        styleMap : new OpenLayers.StyleMap({
            'default' : defaultFeatureStyle
        }),
        onFeatureInsert : function(feature) {
            var debug = 1;
        },
        filter : objfilter
    });

    userObjects = new OpenLayers.Layer.Vector("User objects", {
        strategies : [ new OpenLayers.Strategy.BBOX(), ],
        protocol : new OpenLayers.Protocol.WFS({
            url : "http://localhost:8080/geoserver/wfs",
            featureType : "UserObjects",
            featurePrefix : "rsubd",
            featureNS : "http://isim.vlsu.ru/rsubd",
            srsName : "EPSG:4326",
            geometryName : "THE_GEOM",
            version : "1.1.0"
        }),
        renderers : renderer,
        onFeatureInsert : function(feature) {
            if (feature.state)
                selectControl.select(feature);
        },
        styleMap : new OpenLayers.StyleMap({
            'default' : userObjectStyle
		
        })
    });


    RSUBDMap.addLayers([ openstreetmap, objects, userObjects]);
    RSUBDMap.zoomToExtent(mapextent);

    // add the custom editing toolbar
    var panel = new OpenLayers.Control.Panel({
        'displayClass' : 'customEditingToolbar'
    });

    var navigate = new OpenLayers.Control.Navigation({
        title : "Pan Map"
    });

    var save = new OpenLayers.Control.Button({
        title : "Save Changes",
        trigger : function() {
            if (edit.feature) {
                edit.selectControl.unselectAll();
            }
            for ( var i = 0; i < edit.layer.features.length; i++) {
                if (edit.layer.features[i].state == "Insert") {
                    edit.layer.features[i].attributes.ROUTE_NAME = "123";
                }
            }
            saveStrategy.save();
            alert('saved');
        },
        displayClass : "olControlSaveFeatures"
    });

    // panel.addControls([navigate, save, del, edit, draw]);
    panel.defaultControl = navigate;
    RSUBDMap.addControl(panel);
    // refreshStrategy.activate();
    // refreshStrategy.start();

    function convertToHTML(attrs) {
        str = "<table>";
        pattern = "<tr valign='top'><td><b>{key}</b>:</td> <td>{value}</td></tr>";
        if (attrs.STATE_NUMBER)
            str += pattern.replace("{key}", "State number").replace("{value}",
                attrs.STATE_NUMBER);
        if (attrs.SPEED )
            str += pattern.replace("{key}", "Speed").replace("{value}",
                attrs.SPEED );
        if (attrs.DIRECTION )
            str += pattern.replace("{key}", "Direction").replace("{value}",
                attrs.DIRECTION );
        if (attrs.LON )
            str += pattern.replace("{key}", "Longitude").replace("{value}",
                attrs.LON );
        if (attrs.LAT )
            str += pattern.replace("{key}", "Latitude").replace("{value}",
                attrs.LAT );
        if (attrs.TEXT_HISTORY )
            str += pattern.replace("{key}", "Description").replace("{value}",
                attrs.TEXT_HISTORY );
        
        str += "</table>";
        return str;
    }

    var select = new OpenLayers.Control.SelectFeature([ objects ], {
        clickout : true,
        onSelect : function(feature) {
            select.unselect(feature);
            xy = RSUBDMap.getPixelFromLonLat(new OpenLayers.LonLat(
                feature.geometry.x, feature.geometry.y));// .transform(
            // WGS84Google,
            // WGS84));
            if (feature.popup == null) {
                var innerHTML = convertToHTML(feature.attributes);
                popup = new OpenLayers.Popup.FramedCloud("dragging-issue",
                    new OpenLayers.LonLat(feature.geometry.getCentroid().x,
                        feature.geometry.getCentroid().y),// .transform( WGS84Google,
                    // WGS84),
                    null, '<div style="font-size:.8em">' + innerHTML
                    + '</div>', null, true);
                feature.popup = popup;
                RSUBDMap.addPopup(popup);
            } else {
                feature.popup.toggle();
            }
        }
    });
    RSUBDMap.addControl(select);
    select.activate();

    ResizeMap(37, 55);
    RSUBDMap.zoomTo(6);
   
}