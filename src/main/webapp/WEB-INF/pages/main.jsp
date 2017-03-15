<!DOCTYPE html>
<html>
  <head>
    <link rel="stylesheet" type="text/css" href="/stylesheets/main.css">
    <script type="text/javascript" src="/js/pages/main.js"></script>
    <script type="text/javascript" src="/js/framework/jquery-3.1.1.min.js"></script>
    <!--script type="text/javascript" src="http://code.jquery.com/jquery-3.1.1.min.js"></script-->

    <title>Trade Shift</title>
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no">
    <meta charset="utf-8">
    <% String gooleMapKey = String.valueOf(request.getAttribute("gooleMapKey")); %>
  </head>
  <body>
    <div class="pac-card" id="pac-card">
      <div>
        <div id="title">
          Find Food Facilities Nearby
        </div>
        <div id="type-selector" class="pac-controls">
          <input type="radio" name="type" id="changetype-all" checked="checked">
          <label for="changetype-all">All</label>

          <input type="radio" name="type" id="changetype-establishment">
          <label for="changetype-establishment">Establishments</label>

          <input type="radio" name="type" id="changetype-address">
          <label for="changetype-address">Addresses</label>

          <input type="radio" name="type" id="changetype-geocode">
          <label for="changetype-geocode">Geocodes</label>
        </div>
        <div id="search-radius-selector" class="pac-controls">
          <select id="search-radius"></select>
          <label for="search-radius">Search Radius</label>
        </div>
      </div>
      <div id="pac-container">
        <input id="pac-input" type="text"
            placeholder="Enter a location">
      </div>
    </div>
    <div id="map"></div>
    <div id="infowindow-content">
      <img src="" width="16" height="16" id="place-icon">
      <span id="place-name"  class="title"></span><br>
      <span id="place-address"></span>
    </div>

    <script>
      var map;
      var markers = [];
      var radiusOptions = 20;
      var initialLocation = {lat: -33.8688, lng: 151.2195}; // default: sydney

      // Get the default initial location configured in database of the current user
      window.onload = function() {
        $.getJSON('/initiallocation', function(result, status) {
          if (status === 'success') {
            initialLocation = {lat: result.coordinates.latitude, lng: result.coordinates.longitude};
          }
          initMap(initialLocation);
          initializeRadiusSelector(radiusOptions);
        });
      }


      function initMap(initialLocation) {
        map = new google.maps.Map(document.getElementById('map'), {
          center: initialLocation,
          zoom: 12
        });
        var card = document.getElementById('pac-card');
        var input = document.getElementById('pac-input');
        var types = document.getElementById('type-selector');

        map.controls[google.maps.ControlPosition.TOP_RIGHT].push(card);

        var autocomplete = new google.maps.places.Autocomplete(input);

        // Bind the map's bounds (viewport) property to the autocomplete object,
        // so that the autocomplete requests use the current map bounds for the
        // bounds option in the request.
        autocomplete.bindTo('bounds', map);

        var infowindow = new google.maps.InfoWindow();
        var infowindowContent = document.getElementById('infowindow-content');
        infowindow.setContent(infowindowContent);
        var marker = new google.maps.Marker({
          map: map,
          anchorPoint: new google.maps.Point(0, -29)
        });

        var radiusSelector = document.getElementById('search-radius');
        radiusSelector.addEventListener('change', function() {
          var place = autocomplete.getPlace();
            if (!place || !place.geometry) {
              // User entered the name of a Place that was not suggested and
              // pressed the Enter key, or the Place Details request failed.
              window.alert("No details available for input: " + (place && place.name ? place.name : ""));
              return;
            }

            // If the place has a geometry, then present it on a map.
            if (place.geometry.viewport) {
              map.fitBounds(place.geometry.viewport);
            } else {
              map.setCenter(place.geometry.location);
            }
            var zoom = 15;  // Why default 15? Because it looks good.
            if (radiusSelector.value != null) {
              zoom = getZoomFromRadius(radiusSelector.value);
            }
            map.setZoom(zoom);
            executeSearch(place.geometry.location.lat(), place.geometry.location.lng(), radiusSelector.value);
        });

        autocomplete.addListener('place_changed', function() {
          infowindow.close();
          marker.setVisible(false);
          var place = autocomplete.getPlace();
          if (!place.geometry) {
            // User entered the name of a Place that was not suggested and
            // pressed the Enter key, or the Place Details request failed.
            window.alert("No details available for input: " + place.name);
            return;
          }

          // If the place has a geometry, then present it on a map.
          if (place.geometry.viewport) {
            map.fitBounds(place.geometry.viewport);
          } else {
            map.setCenter(place.geometry.location);
          }
          var zoom = 15;  // Why default 15? Because it looks good.
          if (radiusSelector.value != null) {
            zoom = getZoomFromRadius(radiusSelector.value);
          }
          map.setZoom(zoom);
          marker.setPosition(place.geometry.location);
          marker.setVisible(true);


          // Get all mobile food facilities around the specifid place
          executeSearch(place.geometry.location.lat(), place.geometry.location.lng(), radiusSelector.value);

          var address = '';
          if (place.address_components) {
            address = [
              (place.address_components[0] && place.address_components[0].short_name || ''),
              (place.address_components[1] && place.address_components[1].short_name || ''),
              (place.address_components[2] && place.address_components[2].short_name || '')
            ].join(' ');
          }

          infowindowContent.children['place-icon'].src = place.icon;
          infowindowContent.children['place-name'].textContent = place.name;
          infowindowContent.children['place-address'].textContent = address;
          infowindow.open(map, marker);
        });

        // Sets a listener on a radio button to change the filter type on Places
        // Autocomplete.
        function setupClickListener(id, types) {
          var radioButton = document.getElementById(id);
          radioButton.addEventListener('click', function() {
            autocomplete.setTypes(types);
          });
        }

        setupClickListener('changetype-all', []);
        setupClickListener('changetype-address', ['address']);
        setupClickListener('changetype-establishment', ['establishment']);
        setupClickListener('changetype-geocode', ['geocode']);

        document.getElementById('search-radius')
          .addEventListener('click', function() {
            console.log('Checkbox clicked! New state=' + this.checked);
            //autocomplete.setOptions({strictBounds: this.checked});
            });
      }
    </script>
    <script src="https://maps.googleapis.com/maps/api/js?key=<%=gooleMapKey%>&libraries=places"
        async defer></script>
  </body>
</html>