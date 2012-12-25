var WGS84 = new OpenLayers.Projection("EPSG:4326"); // Transform from WGS 1984;
var WGS84Google = new OpenLayers.Projection("EPSG:900913"); // to Spherical
// Mercator
// Projection;
var RSUBDMap;
var renderer;
var ObjectId;
var editableLayer;
var filter;
var filterStrategy;

// set up the modification tools
var DeleteFeature = OpenLayers.Class(OpenLayers.Control, {
	initialize : function(layer, options) {
		OpenLayers.Control.prototype.initialize.apply(this, [ options ]);
		this.layer = layer;
		this.handler = new OpenLayers.Handler.Feature(this, layer, {
			click : this.clickFeature
		});
	},
	clickFeature : function(feature) {
		// if feature doesn't have a fid, destroy it
		if (feature.fid == undefined) {
			this.layer.destroyFeatures([ feature ]);
		} else {
			feature.state = OpenLayers.State.DELETE;
			this.layer.events.triggerEvent("afterfeaturemodified", {
				feature : feature
			});
			feature.renderIntent = "select";
			this.layer.drawFeature(feature);
		}
	},
	setMap : function(RSUBDMap) {
		this.handler.setMap(RSUBDMap);
		OpenLayers.Control.prototype.setMap.apply(this, arguments);
	},
	CLASS_NAME : "OpenLayers.Control.DeleteFeature"
});

function ResizeMap(lon, lat) {
	RSUBDMap.setCenter(new OpenLayers.LonLat(lon, lat).transform(WGS84,
			WGS84Google));
}

window.onbeforeunload = function() {
	return 'Are you sure want to exit? All unsaved features will be lost!';
}

function showSuccessMsg() {
	alert("Features saved");
};

function showFailureMsg() {
	alert("Internal server error saveing features");
};

function init() {
	// set up a save strategy
	var saveStrategy = new OpenLayers.Strategy.Save();
	saveStrategy.events.register("success", '', showSuccessMsg);
	saveStrategy.events.register("failure", '', showFailureMsg);

	renderer = OpenLayers.Util.getParameters(window.location.href).renderer;
	renderer = (renderer) ? [ renderer ]
			: OpenLayers.Layer.Vector.prototype.renderers;

	// set up projections
	// World Geodetic System 1984 projection
	var WGS84 = new OpenLayers.Projection("EPSG:4326");
	// WGS84 Google Mercator projection
	var WGS84_google_mercator = new OpenLayers.Projection("EPSG:900913");

	// Initialize the RSUBDMap
	// creates a new openlayers RSUBDMap in the <div> html element with
	// id="RSUBDMap"
	RSUBDMap = new OpenLayers.Map("basicMap", {
		controls : [ new OpenLayers.Control.Navigation(),
				new OpenLayers.Control.PanZoomBar(),
				new OpenLayers.Control.DragFeature(),
				new OpenLayers.Control.MousePosition()],
		projection : WGS84,
		displayProjection : WGS84
	});

	// map extent
	var mapextent = new OpenLayers.Bounds(34.17341, 57.24343, 47.06183,
			52.29899).transform(WGS84, WGS84_google_mercator);

	// base layers
	var openstreetmap = new OpenLayers.Layer.OSM();
	
        

	 OpenLayers.ProxyHost = "../../proxy.jsp?";

	filter = new OpenLayers.Filter.Comparison({
		type : OpenLayers.Filter.Comparison.EQUAL_TO,
		property : "OBJECT_ID",
		value : null
	});
	filterStrategy = new OpenLayers.Strategy.Filter({
		filter : filter
	});

	editableLayer = new OpenLayers.Layer.Vector("Objects", {
		strategies : [ new OpenLayers.Strategy.Fixed(), saveStrategy,
				filterStrategy ],
		protocol : new OpenLayers.Protocol.WFS({
			url : "http://localhost:8080/geoserver/wfs",
			featureType : "UserObjects",
			featurePrefix : "rsubd",
			featureNS : "http://isim.vlsu.ru/rsubd",
			geometryName : "THE_GEOM",
			version : "1.1.0"
		}),
		renderers : renderer,
		onFeatureInsert : function(feature) {
			if (feature.state)
				selectControl.select(feature);
		},
		styleMap : new OpenLayers.StyleMap({
			'default' : userEditObjectsStyle
		})
	});
	editableLayer.events.register("beforefeatureremoved", '', function(obj) {
		if (obj.feature.popup) {
			RSUBDMap.removePopup(obj.feature.popup);
		}
	});
	editableLayer.events.register("beforefeatureadded", '', function(obj) {
		obj.feature.attributes.OBJECT_ID = ObjectId;
	});

	RSUBDMap.addLayers([ openstreetmap, editableLayer ]);
	// RSUBDMap.zoomToExtent(mapextent);
	RSUBDMap.zoomTo(9);

	// add the custom editing toolbar
	var panel = new OpenLayers.Control.Panel({
		'displayClass' : 'customEditingToolbar'
	});

	var navigate = new OpenLayers.Control.Navigation({
		title : "Pan"
	});

	var drawPolygon = new OpenLayers.Control.DrawFeature(editableLayer,
			OpenLayers.Handler.Polygon, {
				title : "Polygon",
				displayClass : "olControlDrawPolygon",
				multi : true
			});
	var drawPoint = new OpenLayers.Control.DrawFeature(editableLayer,
			OpenLayers.Handler.Point, {
				title : "Point",
				displayClass : "olControlDrawPoint",
				multi : true
			});
	var drawPath = new OpenLayers.Control.DrawFeature(editableLayer,
			OpenLayers.Handler.Path, {
				title : "Polyline",
				displayClass : "olControlDrawFeaturePath",
				multi : true
			});

	var reshape = new OpenLayers.Control.ModifyFeature(editableLayer, {
		title : "Edit",
		displayClass : "olControlModifyFeature",
		mode : OpenLayers.Control.ModifyFeature.RESHAPE,
		createVertices : true
	});

	var edit = new OpenLayers.Control.ModifyFeature(editableLayer, {
		title : "Reshape",
		displayClass : "olControlReshapeFeature",
		mode : OpenLayers.Control.ModifyFeature.RESIZE
	});
	edit.mode |= OpenLayers.Control.ModifyFeature.ROTATE;
	edit.mode |= OpenLayers.Control.ModifyFeature.DRAG;

	var del = new DeleteFeature(editableLayer, {
		title : "Remove"
	});

	var circleControl = new OpenLayers.Control.DrawFeature(editableLayer,
			OpenLayers.Handler.RegularPolygon, {
				title : "Circle",
				displayClass : "olControlDrawFeatureCircle",
				multi : true,
				handlerOptions : {
					sides : 40
				}
			});

	var save = new OpenLayers.Control.Button({
		title : "Save features",
		displayClass : "olControlSaveFeature",
		trigger : function() {

			for ( var i = 0; i < edit.layer.features.length; i++) {
				edit.layer.features[i].attributes.OBJECT_ID = ObjectId;
				var lonlat = editableLayer.features[0].geometry.getCentroid()
						.transform(WGS84Google, WGS84);
				edit.layer.features[i].attributes.LONGITUDE = lonlat.x;
				edit.layer.features[i].attributes.LATITUDE = lonlat.y;
				//edit.layer.features[i].attributes.DISPATCHER_NAME = document.getElementById('DispathcerName').value;
			}
			saveStrategy.save();
		}
	});

	var deleteAll = new OpenLayers.Control.Button({
		title : "Remove selected",
		displayClass : "olControlSelDelete",
		trigger : function() {
			editableLayer.destroyFeatures(editableLayer.selectedFeatures)
		}
	});

	var selectControl = new OpenLayers.Control.SelectFeature([ editableLayer ],
			{
				title : "Pan",
				displayClass : "olControlPan",
				clickout : true,
				onSelect : function(feature) {
					var bid = (new Date()).getTime();
					//if (feature.popup == null) {
						var innerHTML = propertyProvider.getHTML(bid, feature);
						popup = new OpenLayers.Popup.FramedCloud(
								"featurePopup", new OpenLayers.LonLat(
										feature.geometry.getCentroid().x,
										feature.geometry.getCentroid().y),
								null, innerHTML, null, false);
						feature.popup = popup;
						RSUBDMap.addPopup(popup);

						propertyProvider.initHandlers(feature, selectControl,
								editableLayer, bid);

					//} else {
					//	feature.popup.toggle();
					//}
					feature.popup.updateSize();
				},
				onUnselect : function(feature) {
					if (feature.popup)
						feature.popup.toggle();
				}
			});
	RSUBDMap.addControl(selectControl);
	selectControl.activate();

	var boxSelect = new OpenLayers.Control.SelectFeature([ editableLayer ], {
		title : "Multiple select",
		displayClass : "olControlSelect",
		clickout : true,
		toggle : false,
		multiple : true,
		hover : false,
		toggleKey : "ctrlKey", // ctrl key removes from selection
		multipleKey : "shiftKey", // shift key adds to selection
		box : true
	});
	RSUBDMap.addControl(boxSelect);
	boxSelect.activate();

	panel.addControls([ selectControl, boxSelect, save, deleteAll, del,
			reshape, edit, drawPolygon, drawPath, drawPoint, circleControl ]);
	panel.defaultControl = selectControl;
	RSUBDMap.addControl(panel);

	function saveFeature(feature) {
		// alert(feature.geometry.getCentroid().x);
	}

	function dropFeature(feature) {
		// alert(feature.geometry.getCentroid().y);
	}

	ResizeMap(37.61661, 55.7521);

	window.onbeforeunload = function() {
		// return 'You have unsaved changes!';
	}


}

function setObjectId(rid) {
	ObjectId = rid ? rid : (new Date()).getTime();
	filter.value = rid ? rid : null;
	
	filterStrategy.setFilter(filter);
}


function extend() {
	var ex = editableLayer.getDataExtent();
	if (ex) RSUBDMap.zoomToExtent(ex);
}