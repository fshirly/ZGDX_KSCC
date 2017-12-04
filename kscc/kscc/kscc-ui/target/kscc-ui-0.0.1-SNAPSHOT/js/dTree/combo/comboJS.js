var srcElement = null;
var valueElement = null;
function showTree(item, valueId) {
	srcElement = window.event.srcElement;
	valueElement = document.getElementById(valueId);
    var x = getLeft(item);

	var y = getTop(item) + item.offsetHeight;
	var w = item.offsetWidth;
	blockDTree(x, y, w);
}

function getTop(e) {

	var position = $(e).position();
	var offset = position.top;
	// var offset = e.offsetTop;
	// if (e.offsetParent != null)
	// offset += getTop($(e).offsetParent());
	return offset;
}

function getLeft(e) {

	var position = $(e).position();
	var offset = position.left;
	// var offset = e.offsetLeft;
	// if (e.offsetParent != null)
	// offset += getLeft($(e).offsetParent());
	return offset;
}
function blockDTree(x, y, w) {
	var item = document.getElementById("combdtree");
	item.style.display = 'block';
	item.style.top = y;
	item.style.left = x;
}
function hiddenDTree() {
	var item = document.getElementById("combdtree");
	if (item) {
		item.style.display = 'none';
	}
}
function hiddenDTreeSetVal(controlId, showId, showVal) {
	$("#" + controlId).val(showVal);
	$("#" + controlId).attr("alt", showId);
	var item = document.getElementById("combdtree");
	if (item) {
		item.style.display = 'none';
	}
}
function hiddenDTreeSetValEasyUi(divId, controlId, showId, showVal) {
	$("#" + controlId).val(showVal);
	$("#" + controlId).attr("alt", showId);
	$("#" + divId).dialog('close');
}
function setSrcValue(text, value) {
	srcElement.value = text;
	valueElement.value = value;
	hiddenDTree();
}