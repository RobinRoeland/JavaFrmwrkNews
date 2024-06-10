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
