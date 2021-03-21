import {Injectable} from '@angular/core';

@Injectable({
  providedIn: 'root'
})

export class AppService {
  /** sub nav should be for url param */
  getNavigation(url: string): string {
    return `
{
  "brandItem": [{
    "type": 3,
    "value": "TC4U"
  }],
  "mainNav": [
    {
      "content": [
        {
          "type": 2,
          "value": "bi-house-door"
        },
        {
          "type": 4,
          "value": "Home"
        }
      ],
      "subItems": [],
      "link": "/",
      "disabled": false,
      "divider": false,
      "left": true
    },
    {
      "content": [
        {
          "type": 4,
          "value": "Something"
        }
      ],
      "subItems": [],
      "link": "/something",
      "disabled": false,
      "divider": false,
      "left": true
    },
    {
      "content": [
        {
          "type": 2,
          "value": "bi-person-circle"
        },
        {
          "type": 4,
          "value": "Account"
        }
      ],
      "subItems": [
        {
          "content": [{
            "type": 4,
            "value": "Register"
          }],
          "subItems": [],
          "link": "/register",
          "disabled": false,
          "divider": false,
          "left": true
        },
        {
          "content": [{
            "type": 4,
            "value": "Sign in"
          }],
          "subItems": [],
          "link": "/login",
          "disabled": false,
          "divider": false,
          "left": true
        },
        {
          "content": [{
            "type": 4,
            "value": "Policy"
          }],
          "subItems": [],
          "link": "/policy",
          "disabled": true,
          "divider": true,
          "left": true
        }
      ],
      "link": "",
      "disabled": false,
      "divider": false,
      "left": false
    }
  ],
  "subNav": [
    {
      "content": [
        {
          "type": 4,
          "value": "Top"
        }
      ],
      "subItems": [],
      "link": "#top",
      "disabled": false,
      "divider": false,
      "left": true
    },
    {
      "content": [
        {
          "type": 4,
          "value": "Stats"
        }
      ],
      "subItems": [],
      "link": "#stats",
      "disabled": false,
      "divider": false,
      "left": true
    },
    {
      "content": [
        {
          "type": 4,
          "value": "Exercises"
        }
      ],
      "subItems": [],
      "link": "#exercises",
      "disabled": false,
      "divider": false,
      "left": true
    },
    {
      "content": [
        {
          "type": 4,
          "value": "Training plans"
        }
      ],
      "subItems": [],
      "link": "#plans",
      "disabled": false,
      "divider": false,
      "left": true
    }
  ]
}
    `;
  }
}
