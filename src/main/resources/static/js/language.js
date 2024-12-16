function getLanguageFromCookie() {
    var name = "lang=";
    var decodedCookie = decodeURIComponent(document.cookie);
    var ca = decodedCookie.split(';');
    for (var i = 0; i < ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) == ' ') {
            c = c.substring(1);
        }
        if (c.indexOf(name) == 0) {
            return c.substring(name.length, c.length);
        }
    }
    return "en"; // Varsayılan dil
}

// Dil değiştirme işlevi
document.addEventListener('DOMContentLoaded', function() {
    var langLinks = document.querySelectorAll('.forLanguage a');

    langLinks.forEach(function(link) {
        link.addEventListener('click', function(event) {
            event.preventDefault();
            var xhr = new XMLHttpRequest();
            xhr.open('GET', link.href, true);
            xhr.onload = function() {
                if (xhr.status >= 200 && xhr.status < 400) {
                    console.log("Language changed, reloading page.");
                    window.location.reload();
                }
            };
            xhr.send();
        });
    });
});

// Dil bilgisini konsola yazdırmak için çağır
console.log("Current language: " + getLanguageFromCookie());

var languageUrl = "";
var language = getLanguageFromCookie();

if (language === "zh") {
    languageUrl = "i18n/Chinese.json";
} else {
    languageUrl = "i18n/English.json"; // Varsayılan dil dosyası
}

console.log("Language URL: " + languageUrl); // Dil URL'sini kontrol edin
