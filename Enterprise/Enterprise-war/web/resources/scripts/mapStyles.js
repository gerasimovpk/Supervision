defaultFeatureStyle = new OpenLayers.Style({
    fillOpacity : 1,
    pointRadius : 30,
    fillColor : "blue",
    strokeColor : "blue",
    strokeWidth : 7,
    strokeOpacity : 0.5,
    strokeLinecap : "round",
    externalGraphic : "${getImg}",
    graphicWidth : "${getImgSize}",
    graphicHeight : "${getImgSize}",
    rotation : "${getDirection}",
    label : "${getLabel}",
    fontColor : "#0000AA",
    fontSize : "15px",
    fontFamily : "Verdana",
    fontWeight : "bold",
    fontOpacity : 0.7,
    labelYOffset : "-20",
    labelOutlineColor : "white",
    labelOutlineWidth : 5,
    labelSelect : true
}, {
    context : {
        getImg : function(feature) {
            return "/Enterprise-war/faces/resources/img/objectsSigns/green.png"
        },
        getDirection : function(feature) {
            if (feature.attributes)
                return feature.attributes.DIRECTION;
        },
        getLabel : function(feature) {
            if (feature.attributes)
                if (feature.layer.map.getZoom() > 8) {
                    return feature.attributes.STATE_NUMBER == null ? ""
                    : feature.attributes.STATE_NUMBER;
                } else
                    return "";
        },
        getImgSize : function(feature) {
            if (feature.layer.map.getZoom() > 8) {
                return "33";
            } else {
                return "20";
            }
        }
    }
});

userObjectStyle = new OpenLayers.Style(
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
    fontSize : "10px",
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
            if (feature.attributes)
                if (feature.layer.map.getZoom() > 8) {
                    return feature.attributes.OBJECT_NAME == null ? ""
                    : feature.attributes.OBJECT_NAME;
                } else
                    return "";
        },
        getImg : function(feature) {
            if (feature.attributes)
                return "/Enterprise-war/faces/resources/img/objectsSigns/"
                + (feature.attributes.OBJECT_TYPE ? feature.attributes.OBJECT_TYPE
                    : "DEFAULT") + ".png"
        },
        getImgSize : function(feature) {
            if (feature.attributes)
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



userEditObjectsStyle = new OpenLayers.Style(
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
    fontSize : "10px",
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
            if (feature.attributes)
                return feature.attributes.OBJECT_NAME == null ? ""
                : feature.attributes.OBJECT_NAME;
        },
        getImg : function(feature) {
            if (feature.attributes)
                return "/Enterprise-war/faces/resources/img/objectsSigns/"
                + (feature.attributes.OBJECT_TYPE ? feature.attributes.OBJECT_TYPE
                    : "DEFAULT") + ".png"
        },
        getImgSize : function(feature) {
            if (feature.attributes)
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



