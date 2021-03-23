# FrontApp
[Angular CLI](https://github.com/angular/angular-cli) version 11.2.4.
<br><br>

## Development
### Server
Run `ng serve` for a dev server. Navigate to `http://localhost:4200/`. The app will automatically reload if you change any of the source files.

### Code scaffolding
Run `ng generate component component-name` to generate a new component. You can also use `ng generate directive|pipe|service|class|guard|interface|enum|module`.
<br><br>

## Build
Run `ng build` to build the project. The build artifacts will be stored in the `dist/` directory. Use the `--prod` flag for a production build.
<br><br>

## Tests
### Running unit tests
Run `ng test` to execute the unit tests via [Karma](https://karma-runner.github.io).

### Running end-to-end tests
Run `ng e2e` to execute the end-to-end tests via [Protractor](http://www.protractortest.org/).
<br><br>

# Components

## AppComponent
- The main component.
- All the components on the site are in it.
- Distributes data to other components.

### NavigationComponent
<main-navigation (URLEvent)="changeURL($event)" [position]="'position'" [navigation]="Navigation"></main-navigation>
___
```(URLEvent)="changeURL($event)"``` Emits an event to the parent when the button is clicked<br>
```[position]="'position'"``` navigation position (typ: ```string``` default: ```'fixed'``` options: ```'fixed' | 'bottom'```)<br>
```[navigation]="navigation object"``` navigation object (instance of: ```Navigation``` default: ```null```)

### SocialMediaComponent
<social-media [position]="'position'" [socialMedia]="ISocialMedia"></social-media>
___
```[position]="'position'"``` navigation position (typ: ```string``` default: ```' '``` options: ```'' | 'left' | 'right'```)<br>
```[socialMedia]="social media interface"``` navigation object (instance of: ```ISocialMedia``` default: ```[]```)

### FooterComponent
Contain: NavigationComponent & SocialMediaComponent
___
<page-footer (URLEvent)="changeURL($event)"
             [navPosition]="'position'" [navigation]="Navigation"
             [smPosition]="'position'" [socialMedia]="ISocialMedia">
</page-footer>
___
```(URLEvent)="changeURL($event)"``` Emits an event to the parent when the button is clicked<br>
```[navPosition]="'position'"``` & ```[navigation]="Navigation"``` Params for NavigationComponent<br>
```[smPosition]="'position'"``` & ```[socialMedia]="ISocialMedia"``` Params for SocialMediaComponent<br>

### SectionPageComponent
<section-page [sections]="IPageContent"></section-page>
___
```[sections]="social media interface"``` page object (instance of: ```IPageContent``` default: ```[]```)

### SearchComponent
<search-exercises></search-exercises>
