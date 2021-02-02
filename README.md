# Covid_Statistic
url: https://checkcovid.herokuapp.com/

REST API:

/statistic/{Country} - returns json with cases and deaths. Bouth contains: (date) : value
/statistic/places - returns all countries and places which statistic is tracking

FRONT-END 
/ - returns page which reflects API's information

Steps to deploy to Heroku:

heroku container:push web --app {app_name}

heroku container:release web --app {app_name}

Ready to use!
  Using Intellij:
  Run main class in src->main->java->bycountry.covid->CovidStatisticsApplication
