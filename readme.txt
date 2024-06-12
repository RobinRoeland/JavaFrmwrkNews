Documentatie:



Gebruikte plugins:
- spring boot voor framework
- thymeleaf voor templates
- mysql voor de database (mysql server draait op xampp) 



Ik was op zoek naar hoe een database automatisch aan te maken en vond het antwoord hier:
https://stackoverflow.com/questions/53630079/auto-database-creation-springboot-and-mysql

	'createDatabaseIfNotExist=true' toevoegen aan de URL in de properties file



Voor het gebruik van Thymeleaf (vooral dan voor header/footer) heb ik naast onze cursus wat extra inspiratie gevonden op de ThymeLeaf-site:
https://www.thymeleaf.org/doc/articles/layouts.html

<div th:include="fragments/header :: header">Header</div>
<div th:include="fragments/footer :: footer">Footer</div>



Om een multi-line text te tonen met line-breaks vond ik een oplossing op stackoverflow:
https://stackoverflow.com/questions/30394419/thymeleaf-spring-how-to-keep-line-break

Two of your options:
- Use th:utext - easy setup option, but harder to read and remember
- Create a custom processor and dialect - more involved setup, but easier, more readable future use.

Option 1:
You can use th:utext if you escape the text using the expression utility method #strings.escapeXml( text ) to prevent XSS injection and unwanted formatting - http://www.thymeleaf.org/doc/tutorials/2.1/usingthymeleaf.html#strings
To make this platform independent, you can use T(java.lang.System).getProperty('line.separator') to grab the line separator.
Using the existing Thymeleaf expression utilities, this works:
	<p th:utext="${#strings.replace( #strings.escapeXml( text ),T(java.lang.System).getProperty('line.separator'),'&lt;br /&gt;')}" ></p>

De 2de optie is ingewikkelder omdat je een eigen thymeleaf dialect moet toevoegen.
De API hiervoor is dan ook nog eens afhankelijk van versie tot versie en daarom heb ik deze niet verder bekeken.




Om een beperkte selectie (10 meest recente artikels) te tonen vond ik de nodige functie hier:
https://www.baeldung.com/spring-data-jpa-findfirst-vs-findtop



Voor de validatie van mijn input-template heb ik hier nog even de Form-validatie van Spring en thymleaf error message bekeken:
https://spring.io/guides/gs/validating-form-input
https://www.baeldung.com/spring-thymeleaf-error-messages



Voor de langere text op te kunnen slaan heb ik chatGPT het volgonde gevraagd:
"i'm writing a newspaper website in java and i need to store large paragraphs in a mysql table.
I use thymeleaf and spring boot controllers"

Hier kreeg ik te veel informatie maar dit is hetgeen dat ik heb teruggekregen en heb gebruikt
antwoord: 
"You should define the column in your MySQL table to be able to store large text data. Use the TEXT or LONGTEXT data type for this purpose."
"In your Spring Boot application, create an entity class that maps to this table. Use JPA annotations to define the mapping."
"@Column(columnDefinition = "LONGTEXT", nullable = false)
    private String content;"
	
	
Hierna heb ik nog voor de zekerheid gevraagd wat het maximum aantal characters is en kreeg dit terug: 
"TINYTEXT: 255 characters (255 bytes)
TEXT: 65,535 characters (64 KB)
MEDIUMTEXT: 16,777,215 characters (16 MB)
LONGTEXT: 4,294,967,295 characters (4 GB)"

Hier zag ik dat LONGTEXT eigenlijk te veel is en ben ik naar MEDIUMTEXT gegaan.


Na dit aan te passen en te testen kreeg ik een foutmelding ("HTTP Status 400 – Bad Request") dat ik in dezelfde chat gevraagd heb, dit was de oplossing dat ik kreeg:
"Ensure that your server can handle large POST requests. In Spring Boot, 
you can configure this by setting the spring.servlet.multipart.max-request-size and spring.servlet.multipart.max-file-size properties in your application.properties or application.yml file."

"spring.servlet.multipart.max-file-size=10MB
spring.servlet.multipart.max-request-size=10MB"

Hierna kreeg ik nog een andere fout ("Request header is too large") die ik opnieuw gevraagd heb in dezelfde chat, dit is de oplossing van deze fout:
"The error "Request header is too large" typically occurs when the HTTP request headers exceed the server's maximum allowed size."
"You can configure the maximum header size for Tomcat in the application.properties file by setting the server.max-http-header-size property:
server.max-http-header-size=32768"