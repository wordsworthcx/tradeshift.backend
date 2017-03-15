var MILE_TO_KM = 1.609344;
var KM_TO_MILE = 0.621371;
var MIN_ZOOM = 12;

function addMarker(map, position, label, info) {
  var marker = new google.maps.Marker({
    position: position,
    map: map,
    label: label
  });
  var infoWindow = new google.maps.InfoWindow({
    content: info
  });
  marker.addListener('click', function() {
    infoWindow.open(map, marker);
  });
  markers.push(marker);
}

function executeSearch(latitude, longitude, radius) {
  deleteMarkers();
  if (!latitude || !longitude || !radius) {
    window.alert("Inputs are invalid, please check the latitidue, longitude and radius.");
    return;
  }
  $.getJSON('/mobilefoodfacilitiesaround', {latitude: latitude, longitude: longitude, radius: radius}, function(result, status) {
    if (status === 'success' && result.length > 0) {
      for (var i = 0; i < result.length; i++) {
        var entity = result[i];
        if (entity != null && entity !== '') {
          displayEntity(entity);
        }
      }
    } else {
      alert('No mobile food facilities found around this place within radius ' + radius + ' km');
    }
  });
}

function displayEntity(entity) {
  if (entity != null && entity !== '') {
    if (entity.latitude != null && entity.longitude != null) {
      var position = {lat: entity.latitude, lng: entity.longitude};
      var label = '?';
      if(entity.eventCode != null && entity.eventCode !== '') {
        label = entity.eventCode.charAt(0).toUpperCase();
      }
      var info =
      '<div>' +
        '<h1 class="firstHeading">' + entity.eventName + '</h1>' +
        '<div class="bodyContent"><p>' + entity.description + '</p></div>' +
      '</div>';
      addMarker(map, position, label, info);
    }
  }
}

function initializeRadiusSelector(optionsLength) {
  var selector = document.getElementById('search-radius');
  for (var i = 1; i <= optionsLength; i++) {
      var option = document.createElement('option');
      option.value = i;
      option.text = i + ' km';
      selector.appendChild(option);
  }
}

// Sets the map on all markers in the array.
function setMapOnAll(map) {
  for (var i = 0; i < markers.length; i++) {
    markers[i].setMap(map);
  }
}

// Removes the markers from the map, but keeps them in the array.
function clearMarkers() {
  setMapOnAll(null);
}

// Shows any markers currently in the array.
function showMarkers() {
  setMapOnAll(map);
}

// Deletes all markers in the array by removing references to them.
function deleteMarkers() {
  clearMarkers();
  markers = [];
}

// Calculate the best zoom based on the map display radius. The zoom should not be less than MIN_ZOOM=12.
function getZoomFromRadius(radius) {
  var zoom = Math.round(14 - Math.log(radius * KM_TO_MILE) / Math.LN2);
  return Math.max(zoom, MIN_ZOOM);
}