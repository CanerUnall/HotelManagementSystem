document.addEventListener('DOMContentLoaded', function () {
            var roomDropdown = document.getElementById('roomDropdown');
            var dropdownMenu = document.getElementById('dropdownMenu');

            roomDropdown.addEventListener('mouseover', function() {
                dropdownMenu.style.display = 'block';
            });

            roomDropdown.addEventListener('mouseout', function() {
                dropdownMenu.style.display = 'none';
            });

            dropdownMenu.addEventListener('mouseover', function() {
                dropdownMenu.style.display = 'block';
            });

            dropdownMenu.addEventListener('mouseout', function() {
                dropdownMenu.style.display = 'none';
            });

            // Load appropriate content when a dropdown item is clicked
            var links = dropdownMenu.getElementsByTagName('a');
            for (var i = 0; i < links.length; i++) {
                links[i].addEventListener('click', function(e) {
                    e.preventDefault();
                    var url = this.getAttribute('href');
                    document.getElementById('fragmentContainer').innerHTML = '<div class="spinner-border text-primary" role="status"><span class="sr-only">Loading...</span></div>';
                    $('#fragmentContainer').load(url);
                });
            }

            // Optional: Hide dropdown menu if clicked outside
            document.addEventListener('click', function(event) {
                if (!roomDropdown.contains(event.target) && !dropdownMenu.contains(event.target)) {
                    dropdownMenu.style.display = 'none';
                }
            });
        });