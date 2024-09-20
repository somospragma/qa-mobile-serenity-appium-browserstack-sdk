<h1 align="center">
  <br>
  <a href="http://www.amitmerchant.com/electron-markdownify"><img src="https://f.hubspotusercontent20.net/hubfs/2829524/Copia%20de%20LOGOTIPO_original-2.png"></a>
  <br>
  qa-mobile-serenity-appium-browserstack-sdk
  <br>
</h1>

<h4 align="center">Proyecto base de <a href="https://github.com/karatelabs/karate" target="_blank">Pragma</a>.</h4>


<p align="center">
  <a href="https://www.oracle.com/java/technologies/javase-jdk16-downloads.html">
    <img src="https://img.shields.io/badge/Java-16-orange.svg" alt="Java">
  </a>
  <a href="https://serenity-bdd.info/">
    <img src="https://img.shields.io/badge/Serenity-BDD-blueviolet.svg" alt="Serenity">
  </a>
  <a href="https://appium.io/">
    <img src="https://img.shields.io/badge/Appium-Mobile_Testing-blue.svg" alt="Appium">
  </a>
  <a href="https://www.browserstack.com/">
    <img src="https://img.shields.io/badge/BrowserStack-Cross_Browser_Testing-lightgrey.svg" alt="BrowserStack">
  </a>
  <a href="https://cucumber.io/">
    <img src="https://img.shields.io/badge/Cucumber-BDD-green.svg" alt="Cucumber">
  </a>
  <a href="https://maven.apache.org/">
    <img src="https://img.shields.io/badge/Maven-Build_Tool-red.svg" alt="Maven">
  </a>
</p>

Integración de Appium, Serenity y BrowserStack SDK.

<p align="center">
  <a href="#topicos">Topicos</a> •
  <a href="#tecnologias">Tecnologias</a> •
  <a href="#consideraciones">Consideraciones</a> •
  <a href="#descarga">Descarga</a> •
  <a href="#instalación-y-ejecución">Instalación y ejecución</a> •
  <a href="#autores">Autores</a> •
  <a href="#relacionados">Relacionados</a> •
  <a href="#roadmap">Roadmap</a>
</p>


## Topicos

* Java 16
* Maven
* Cucumber
* Serenity BDD
* Appium 2
* Screenplay
* Mobile Testing

## Tecnologias
### This project required:
- [JDK java] version 16
- [Serenity] version 4
- [Maven] last version
- [Appium] version 2.0.1

## Consideraciones
* Puedes modificar los reintentos de pruebas fallidas en pom.xml cambiando el valor de `<max.attempts.rerun.test>2</max.attempts.rerun.test>`
* Solo se puede ejecutar desde comandos.

## Descarga
Para clonar está aplicación desde la linea de comando:

```bash
git clone https://github.com/somospragma/qa-transversal-proyecto-base-manejo-base-de-datos-java
cd qa-transversal-proyecto-base-manejo-base-de-datos-java
git remote remove origin
git remote add origin URL_DE_TU_NUEVO_REPOSITORIO
git push -u origin master
```
Nota: Asegúrate de reemplazar URL_DE_TU_NUEVO_REPOSITORIO con la URL del repositorio que creaste en tu cuenta de GitHub.

Puedes descargar el proyecto en el enlace [download](https://github.com/somospragma/qa-transversal-proyecto-base-manejo-base-de-datos-java)

## 🛠️ Instalación

* Clone the repo
* Replace YOUR_USERNAME and YOUR_ACCESS_KEY with your BrowserStack access credentials in execution command. Alternatively you can configure them in browserstack.yml
* Replace app bs url in execution command, this url is generated on Browserstack after of upload app. Alternatively you can configure it in browserstack.yml
* Install dependencies `mvn install`
* You can configure environment variables for all sample repos (see Notes) or update `serenity.conf` file with your [BrowserStack Username and Access Key](https://www.browserstack.com/accounts/settings)


##   Run tests :

### Running your tests
#### If you use profiles (configured in pom.xml)
- To run a sample test, run `mvn verify -P sample-test -Dbrowserstack.userName=USER -Dbrowserstack.accessKey=KEY -Dbrowserstack.app=bs://BS`
#### If you use specify runner without profiles (typing class name of the runner)
- To run a sample test, run `mvn verify -Drunner=SampleTest -Dbrowserstack.userName=USER -Dbrowserstack.accessKey=KEY -Dbrowserstack.app=bs://BS`
#### If you use local app
- To run a sample test, run `mvn verify -Drunner=SampleTest -Dbrowserstack.userName=USER -Dbrowserstack.accessKey=KEY -Dbrowserstack.app=app-flutter-pruebas.apk`

Understand how many parallel sessions you need by using our [Parallel Test Calculator](https://www.browserstack.com/automate/parallel-calculator?ref=github)

#### Notes:
    * replace in the command USER, KEY credentials and BS of app or path of local app

## Reports
* You can view your test results on the [BrowserStack Automate dashboard](https://www.browserstack.com/app-automate)
* You can view serenity report on target directory

## Autores


| [<img src="https://gitlab.com/uploads/-/system/user/avatar/13437423/avatar.png?width=400" width=115><br><sub>Mauro L. Ibarra P.</sub>](https://gitlab.com/mauro.ibarrap) <br/> |
:------------------------------------------------------------------------------------------------------------------------------------------------------------------------------:|

## Relacionados

- [proyecto-base-serenity-bdd-screenplay-browsers-and-utilities](https://github.com/somospragma/qa-web-proyecto-base-serenity-bdd-screenplay-browsers-and-utilities)

