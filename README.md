
# iFood Backend Advanced Test

[![Codacy Badge](https://api.codacy.com/project/badge/Grade/3a9f456255d045c8b27f49f55bf30aa5)](https://www.codacy.com/app/edylle/ifood-backend-advanced-test?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=edylle/ifood-backend-advanced-test&amp;utm_campaign=Badge_Grade)

This project is my personal solution for the iFood Backend Advanced Test.
<br/>

The test states as follow:
<br/>
"Create a micro-service able to accept RESTful requests receiving as parameter either city name or lat long coordinates and returns a playlist (only track names is fine) suggestion according to the current temperature."
<br/>

For the temperature information I made use of [OpenWeatherMaps API](https://openweathermap.org/) and [Sporify](https://developer.spotify.com/) for the song tracks.
<br/>

This project was built over very well known and trusted technologies and frameworks:

 - Java 8;
 - Spring Boot;
 - Maven;
 
If you need to apply any changes in the project, such as OpenWeather and Spotify credentials, you can do so by modifying applications.properties file located at "src/main/resources" directory.
 <br />
 <br />
 Since this project is managed by Maven, you can generate a build of it by entering the following command in terminal (make sure you are in the same directory where project's pom.xml file is):

    mvn clean package

This command will generate a .jar file named "ifood-backend-advanced-test.jar" in a folder called "target" inside the project's directory.
<br />
In order to run the build, enter the following command line in terminal (make sure you are in the same directory where ifood-backend-advanced-test.jar file is):

    java -jar ifood-backend-advanced-test.jar

Once the system is up and running, if you haven't made any changes in applications.properties, it is possible to access its endpoints by the URI http://localhost:8080/ifood/suggestion


## API REST endpoints

### 1. Fetch Tracks

##### 1.1. GET by the name of a city
	1. URL (GET): http://server_ip:server_port/ifood/suggestion/city?name=Campinas

	2. JSON response (example)

```javascript
{
    "status": "OK",
    "code": 200,
    "messages": [
        "MAKING NEW REQUEST FOR THE CITY: Campinas",
        "CALLS IN THE LAST MINUTE: 1",
        "TEMPERATURE: 21.0"
    ],
    "result": [
        "Só Hoje",
        "Era um Garoto, Que Como Eu, Amava os Beatles e os Rolling Stones",
        "I Got It (feat. Brooke Candy, CupcakKe and Pabllo Vittar)",
        "O Papa É Pop - Live",
        "Believer",
        "Candy Pop",
        "Perfeita Simetria",
        "P.O.P (Piece Of Peace) pt.1",
        "I Love It (feat. Charli XCX)",
        "Papo De Jacaré",
        "O Rap Tá Pop",
        "Blue (Da Ba Dee) - Gabry Ponte Ice Pop Radio",
        "Say Ok",
        "Out Of My Head (feat. Tove Lo and ALMA)",
        "Cups (Pitch Perfect’s “When I’m Gone”) - Pop Version",
        "Bubble Pop!",
        "E Daí? - Ao Vivo",
        "Waking Lions",
        "Pop Style",
        "O Papa é Pop"
    ]
}
```

##### 1.2. GET by latitude and longitude
	1. URL (GET): http://server_ip:server_port/ifood/suggestion/coordinates?lat=43.7&lon=-79.42

	2. JSON response (example)

```javascript
{
    "status": "OK",
    "code": 200,
    "messages": [
        "MAKING NEW REQUEST FOR THE LATITUDE 43.7 AND LONGITUDE -79.42",
        "CALLS IN THE LAST MINUTE: 2",
        "TEMPERATURE: -2.25"
    ],
    "result": [
        "Requiem: Lacrimosa",
        "Sonata No. 14 \"Moonlight\" in C-Sharp Minor\", Op. 27 No. 2: I. Adagio sostenuto",
        "Gymnopedie",
        "The Ludlows",
        "Unaccompanied Cello Suite No. 1 in G Major, BWV 1007: I. Prélude",
        "Air on a G String",
        "Adagio Albinoni",
        "Mass in B minor, BWV 232, Part II, Symbolum Nicenum: Credo in unum Deum - Live",
        "Johannespassion, BWV 245, Pt. 1: No. 1 Chor: Herr, unser Herrscher",
        "Johannespassion, BWV 245, Pt. 1: No. 2 a-e Rezitativ: Jesus ging mit seinen Jüngern",
        "Johannespassion, BWV 245, Pt. 1: No. 3 Choral: O große Lieb",
        "Johannespassion, BWV 245, Pt. 1: No. 4 Rezitativ: Auf daß das Wort erfüllet würde",
        "Johannespassion, BWV 245, Pt. 1: No. 5 Choral: Dein Will gescheh, Herr Gott, zugleich",
        "Johannespassion, BWV 245, Pt. 1: No. 6 Rezitativ: Die Schar aber und der Oberhauptmann",
        "Johannespassion, BWV 245, Pt. 1: No. 7 Arie: Von den Stricken meiner Sünden",
        "Johannespassion, BWV 245, Pt. 1: No. 9 Arie: Ich folge dir gleichfalls mit freudigen Schritten",
        "Johannespassion, BWV 245, Pt. 1: No. 10 Rezitativ: Derselbige Jünger war dem Hohenpriester bekannt",
        "Violin Sonata No. 3 in E Major, BWV 1016 (Arr. D. Rohmann & L. Fassang for 5-String Cello & Organ): I. Adagio",
        "Johannespassion, BWV 245, Pt. 1: No. 11 Choral: Wer hat dich so geschlagen",
        "Johannespassion, BWV 245, Pt. 1: No. 12 a-c Rezitativ: Und Hannas sandte ihn gebunden"
    ]
}
```
