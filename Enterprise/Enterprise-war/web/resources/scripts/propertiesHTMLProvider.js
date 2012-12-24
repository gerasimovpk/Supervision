var propertyProvider = {

	getHTML : function(id, feature) {


		str = "<table>";
		pattern = "<tr {hidden} id={id} valign='top'><td><b>{key}</b>:</td> <td>{value}</td></tr>";

		str += pattern
				.replace("{key}", "Name")
				.replace("{id}", "ObjectNameRow" + id)
				.replace(
						"{value}",
						"\n"
								+ "<input id='ObjectName"
								+ id
								+ "' value='"
								+ escape(feature.attributes.OBJECT_NAME ? feature.attributes.OBJECT_NAME
										: "") + "'/>").replace("{hidden}", "");

		str += pattern
				.replace("{key}", "Object type")
				.replace("{id}", "ObjectTypeRow" + id)
				.replace("{hidden}", "")
				.replace(
						"{value}",
						"\n"
								+ "<select id='ObjectType"
								+ id
								+ "' value='"
								+ escape(feature.attributes.OBJECT_TYPE ? feature.attributes.OBJECT_TYPE
										: ""))
				+ "'>\n"

				+ "<option {selected} value='SKP'>Checkpoint</option>\n"
						.replace(
								"{selected}",
								feature.attributes.OBJECT_TYPE == "SKP" ? "selected='true'"
										: "")

				+ "<option {selected} value='OVERPASS'>Overpass</option>\n"
						.replace(
								"{selected}",
								feature.attributes.OBJECT_TYPE == "OVERPASS" ? "selected='true'"
										: "")

				+ "<option {selected} value='RAMPANT'>Rampant</option>\n"
						.replace(
								"{selected}",
								feature.attributes.OBJECT_TYPE == "RAMPANT" ? "selected='true'"
										: "")

				+ "<option {selected} value='PILLAR'>Pillar</option>\n"
						.replace(
								"{selected}",
								feature.attributes.OBJECT_TYPE == "PILLAR" ? "selected='true'"
										: "")

				+ "<option {selected} value='RAIL_CROSSING'>Rail Crossing</option>\n"
						.replace(
								"{selected}",
								feature.attributes.OBJECT_TYPE == "RAIL_CROSSING" ? "selected='true'"
										: "")

				+ "<option {selected} value='TUNNEL'>Tonnel</option>\n"
						.replace(
								"{selected}",
								feature.attributes.OBJECT_TYPE == "TUNNEL" ? "selected='true'"
										: "")

				+ "<option {selected} value='BRIDGE'>Bridge</option>\n"
						.replace(
								"{selected}",
								feature.attributes.OBJECT_TYPE == "BRIDGE" ? "selected='true'"
										: "")

				+ "<option {selected} value='FERRY'>Ferry</option>\n"
						.replace(
								"{selected}",
								feature.attributes.OBJECT_TYPE == "FERRY" ? "selected='true'"
										: "")

				+ "<option {selected} value='VIADUCT'>Viaduct</option>\n"
						.replace(
								"{selected}",
								feature.attributes.OBJECT_TYPE == "VIADUCT" ? "selected='true'"
										: "")

				
				+ "<option {selected} value='CROSSWALK'>Crosswalk</option>\n"
						.replace(
								"{selected}",
								feature.attributes.OBJECT_TYPE == "CROSSWALK" ? "selected='true'"
										: "")

				
				+ "<option {selected} value='POI'>POI</option>\n"
						.replace(
								"{selected}",
								feature.attributes.OBJECT_TYPE == "POI" ? "selected='true'"
										: "")

				+ "<option {selected} value='CIVIAL'>Civial</option>\n"
						.replace(
								"{selected}",
								feature.attributes.OBJECT_TYPE == "CIVIAL" ? "selected='true'"
										: "")

				+ "<option {selected} value='INDUSTRIAL'>Industrial</option>\n"
						.replace(
								"{selected}",
								feature.attributes.OBJECT_TYPE == "INDUSTRIAL" ? "selected='true'"
										: "")

				+ "</select>";

		
		str += "</table>";
		str += "</br>";
		str += "<input type='button' value='Save' id='saveElementButton"
				+ id + "'>";
		if (feature.attributes.OBJECT_TYPE) {
			str += "<input type='button' value='Cancel' id='cancelButton"
					+ id + "'>";
		}
		str += "<input type='button' value='Delete' id='dropElementButton"
				+ id + "'>";

		return str;
	}

	,
	initHandlers : function(feature, selectControl, editableLayer, id) {
		document.getElementById("saveElementButton" + id).onclick = function() {

			feature.attributes.OBJECT_NAME = document
					.getElementById("ObjectName" + id).value;

			feature.attributes.OBJECT_TYPE = document
					.getElementById("ObjectType" + id).value;

			
			selectControl.unselect(feature);
			if (!feature.state)
				feature.state = OpenLayers.State.UPDATE;
		};

		if (feature.attributes.OBJECT_TYPE) {
			document.getElementById("cancelButton" + id).onclick = function() {
				selectControl.unselect(feature);
			};
		}
		document.getElementById("dropElementButton" + id).onclick = function() {
			selectControl.unselect(feature);
			if (feature.state == "Insert") {
				editableLayer.removeFeatures(feature);
				RSUBDMap.removePopup(popup);
			}
		};

		document.getElementById("ObjectType" + id).onchange = function() {			
			feature.popup.updateSize();
		};
	}

}