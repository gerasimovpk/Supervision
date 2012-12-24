defaultFeatureStyle = new OpenLayers.Style(
		{
			fillOpacity : "${getOpacity}",
			pointRadius : 5,
			fillColor : "${getColor}",
			strokeColor : "${getColor}",
			strokeWidth : "${getCorridorWidth}",
			strokeOpacity : 0.5,
			strokeLinecap : "round",
			externalGraphic : "${getImg}",
			graphicWidth : "${getImgSize}",
			graphicHeight : "${getImgSize}",
			label : "${getLabel}",
			fontColor : "#0000AA",
			fontSize : "15px",
			fontFamily : "Verdana",
			fontWeight : "bold",
			fontOpacity : 0.7,
			labelOutlineColor : "white",
			labelOutlineWidth : 5,
			labelSelect : true,
			labelAlign : "cm",
			labelYOffset : "-25"
		},
		{
			context : {
				getOpacity : function(feature) {
					return feature.geometry ? feature.geometry.id
							.indexOf("Point") >= 0 ? 1 : 0.5 : 0.5;
				},
				getColor : function(feature) {
					return "orange";
				},
				getLabel : function(feature) {
					// if(feature.layer.map.getZoom() > 8) {
					return feature.attributes.OBJECT_NAME == null ? ""
							: feature.attributes.OBJECT_NAME;
					// }
					// else return "";
				},
				getImg : function(feature) {
					return "objEditor/resources/img/objectsSigns/"
							+ (feature.attributes.OBJECT_TYPE ? feature.attributes.OBJECT_TYPE
									: "DEFAULT") + ".png"
				},
				getImgSize : function(feature) {
					if (feature) {
						if (feature.attributes.OBJECT_TYPE == "SKP") {
							return "16";
						}
						if (!feature.layer) {
							return "20";
						}
						if (!feature.attributes.OBJECT_TYPE) {
							return "16";
						}
						if (feature.layer.map.getZoom() > 8) {
							return "33";
						} else {
							return "20";
						}
					}

				}

			}
		});

editingStyle = new OpenLayers.Style(
		{
			fillOpacity : "${getOpacity}",
			pointRadius : 5,
			fillColor : "blue",
			strokeColor : "blue",
			context : {
				getOpacity : function(feature) {
					return feature.geometry ? feature.geometry.id
							.indexOf("Point") >= 0 ? 1 : 0.5 : 0.5;
				}
			}
		});