import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})

export class AppService {
  private prefix = '/api';

  constructor(private http: HttpClient) { }

  apiPostRequest(endpoint: string, data: any): any {
    return this.http.post<Response>(`${this.prefix}/${endpoint}`, data);
  }

  apiPutRequest(endpoint: string, data: any): any {
    return this.http.put<Response>(`${this.prefix}/${endpoint}`, data);
  }

  apiGetRequest(endpoint: string, options?: object): any {
    return this.http.get<Response>(`${this.prefix}/${endpoint}`, options);
  }

  /** sub nav should be for url param */
  getNavigation(url: string): string {
    // todo
    const tmp_isUserLogin = !!sessionStorage.getItem('username');
    let account;
    if (tmp_isUserLogin) {
      account = `
      {
          "content": [{
            "type": 4,
            "value": "Profile"
          }],
          "subItems": [],
          "link": "/profile",
          "disabled": false,
          "divider": false,
          "left": true
        },
        {
          "content": [{
            "type": 4,
            "value": "Logout"
          }],
          "subItems": [],
          "link": "/logout",
          "disabled": false,
          "divider": false,
          "left": true
        },
      `;
    }
    else {
      account = `
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
            "value": "Login"
          }],
          "subItems": [],
          "link": "/login",
          "disabled": false,
          "divider": false,
          "left": true
        },
      `;
    }

    if (url === '/') {
      return `
{
  "brandItem": {
    "type": 3,
    "value": "TC4U"
  },
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
      "divider": false
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
        ${account}
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
      "divider": false
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
    },
    {
      "content": [
        {
          "type": 4,
          "value": "Measurements"
        }
      ],
      "subItems": [],
      "link": "#measurements",
      "disabled": false,
      "divider": false,
      "left": true
    }
  ]
}
    `;
    }
    else if (url === '/something') {
      return `
{
  "brandItem": {
    "type": 3,
    "value": "TC4U"
  },
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
        ${account}
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
      "divider": false
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
          "value": "Rogal"
        }
      ],
      "subItems": [],
      "link": "#rogal",
      "disabled": false,
      "divider": false,
      "left": true
    }
  ]
}
    `;
    }
    else {
      return `
{
  "brandItem": {
    "type": 3,
    "value": "TC4U"
  },
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
        ${account}
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
      "divider": false
    }
  ],
  "subNav": []
}
    `;
    }
  }

  getSocialMedia(): string {
    return `
{
  "socialMedia": [
    {
      "name": "Facebook",
      "link": "https://www.facebook.com/",
      "icon": "bi-facebook"
    },
    {
      "name": "Twitter",
      "link": "https://www.twitter.com/",
      "icon": "bi-twitter"
    }
  ]
}
    `;
  }

  getFooterInfos(): string {
    return `
{
  "infos": {
    "text": "<b>Lorem ipsum dolor sit amet</b><br>Aliquam purus massa, lobortis <b>non</b> bibendum sed, vestibulum quis arcu. Vestibulum sodales imperdiet mollis. Mauris turpis dolor, gravida vel porttitor dictum, facilisis in eros. Interdum et malesuada fames ac ante ipsum primis in faucibus.",
    "copyright": "© Copyright 2021 — G. Barszcz — J. Tusiński — J. Ostrowski — K. Słotwiński — "
  }
}
    `;
  }

  getPageContent(url: string): string {
    if (url === '/') {
      return `
{
    "type": "section",
    "sections": [
      {
        "metaData": {
          "id": "top",
          "type": null,
          "background": "image-hero_container"
        },
        "headerInfos": {
          "title": "Lorem ipsum dolor sit amet",
          "subTitle": "Aenean tempor porta ante non aliquam",
          "insideFirstColumn": true
        },
        "content": [{
          "column": "col-12 col-md-11 col-lg-7 col-xxl-6",
          "text": "<p>Aliquam purus massa, lobortis non bibendum sed, vestibulum quis arcu. Vestibulum sodales imperdiet mollis. Mauris turpis dolor, gravida vel porttitor dictum, facilisis in eros. Interdum et malesuada fames ac ante ipsum primis in faucibus.</p>&#10;<a href='/login' class='btn btn_alt'>Login</a>&#10;<a href='/register' class='btn'>Register</a>",
          "component": null
        }],
        "background": "assets/images/gymguy.jpg"
      },
        {
        "metaData": {
          "id": "stats",
          "type": "section_th",
          "background": null
        },
        "headerInfos": {
          "title": "Stats",
          "subTitle": "Aenean tempor porta ante non aliquam",
          "insideFirstColumn": true
        },
        "content": [
          {
            "column": "col-12 col-md-8 col-lg-6",
            "text": "<p>Aliquam purus massa, lobortis non bibendum sed, vestibulum quis arcu. Vestibulum sodales imperdiet mollis. Mauris turpis dolor, gravida vel porttitor dictum, facilisis in eros. Interdum et malesuada fames ac ante ipsum primis in faucibus.</p>&#10;<a href='#' class='btn'>Learn more</a>",
            "component": null
          },
          {
            "column": "col-12 col-md-8 col-lg-6 img_container",
            "text": "<img src='assets/images/arrow.png'/>",
            "component": null
          }
        ],
        "background": null
      },
        {
        "metaData": {
          "id": "exercises",
          "type": "section_pr",
          "background": "background_video"
        },
        "headerInfos": {
          "title": "Exercises",
          "subTitle": "Aliquam purus massa, lobortis non bibendum sed, vestibulum quis arcu. Vestibulum sodales imperdiet mollis.",
          "insideFirstColumn": false
        },
        "content": [
          {
            "column": "col-12 col-lg-6",
            "text": "<p>Aliquam purus massa, lobortis non bibendum sed, vestibulum quis arcu. Vestibulum sodales imperdiet mollis. Mauris turpis dolor, gravida vel porttitor dictum, facilisis in eros. Interdum et malesuada fames ac ante ipsum primis in faucibus.</p>&#10;<a href='#' class='btn'>Go to exercises atlas</a>",
            "component": null
          },
          {
            "column": "col-12 col-md-8 col-lg-6",
            "text": "<p>Lorem ipsum dolor sit amet. Aenean tempor porta ante non aliquam. Aliquam purus massa, lobortis non bibendum sed, vestibulum quis arcu.</p>",
            "component": "search"
          }
        ],
        "background": "assets/videos/trening.mp4"
      },
        {
        "metaData": {
          "id": "plans",
          "type": "section_se",
          "background": null
        },
        "headerInfos": {
          "title": "Training plans",
          "subTitle": "Aliquam purus massa, lobortis non bibendum sed, vestibulum quis arcu. Vestibulum sodales imperdiet mollis.",
          "insideFirstColumn": false
        },
        "content": [
          {
            "column": "col-12",
            "text": "<p>Aliquam purus massa, lobortis non bibendum sed, vestibulum quis arcu. Vestibulum sodales imperdiet mollis. Mauris turpis dolor, gravida vel porttitor dictum, facilisis in eros. Interdum et malesuada fames ac ante ipsum primis in faucibus.</p>",
          "component": null
          },
          {
            "column": "col-12 col-sm-6 col-lg-3",
            "text": "<div class='card'><div class='icon'><i class='bi bi-calendar3'></i></div><p>Aliquam purus massa, lobortis non bibendum sed, vestibulum quis arcu. </p><a href='#' class='btn btn_alt'>Learn more</a></div>",
            "component": null
          },
          {
            "column": "col-12 col-sm-6 col-lg-3",
            "text": "<div class='card'><div class='icon'><i class='bi bi-card-checklist'></i></div><p>Aliquam purus massa, lobortis non bibendum sed, vestibulum quis arcu. </p><a href='#' class='btn btn_alt'>Learn more</a></div>",
            "component": null
          },
          {
            "column": "col-12 col-sm-6 col-lg-3",
            "text": "<div class='card'><div class='icon'><i class='bi bi-clock-history'></i></div><p>Aliquam purus massa, lobortis non bibendum sed, vestibulum quis arcu. </p><a href='#' class='btn btn_alt'>Learn more</a></div>",
            "component": null
          },
          {
            "column": "col-12 col-sm-6 col-lg-3",
            "text": "<div class='card'><div class='icon'><i class='bi bi-heart'></i></div><p>Aliquam purus massa, lobortis non bibendum sed, vestibulum quis arcu. </p><a href='#' class='btn btn_alt'>Learn more</a></div>",
            "component": null
          }
        ],
        "background": null
      },
        {
        "metaData": {
          "id": "measurements",
          "type": "section_pr",
          "background": "background_video"
        },
        "headerInfos": {
          "title": "Measurements",
          "subTitle": "Aliquam purus massa, lobortis non bibendum sed, vestibulum quis arcu. Vestibulum sodales imperdiet mollis.",
          "insideFirstColumn": false
        },
        "content": [
          {
            "column": "col-12 col-lg-6",
            "text": "<p>Aliquam purus massa, lobortis non bibendum sed, vestibulum quis arcu. Vestibulum sodales imperdiet mollis. Mauris turpis dolor, gravida vel porttitor dictum, facilisis in eros. Interdum et malesuada fames ac ante ipsum primis in faucibus.</p>&#10;<a href='/measurement' class='btn'>Go to measurements</a>",
            "component": "Measurements"
          }
        ],
        "background": "assets/videos/trening.mp4"
      }
    ]
}
    `;
    } else if (url === '/register') {
      return `
{
    "type": "section",
    "sections": [
      {
        "metaData": {
          "id": "register",
          "type": "section_se",
          "background": "background_video"
        },
        "headerInfos": {
          "title": "Lorem ipsum",
          "subTitle": "Aenean tempor porta ante non aliquam",
          "insideFirstColumn": true
        },
        "content": [
          {
            "column": "col-12 col-lg-6",
            "text": "<p>Aliquam purusmassa, lobortis non bibendum sed, vestibulum quis arcu. Vestibulum sodales imperdiet mollis. Mauris turpis dolor, gravida vel porttitor dictum, facilisis in eros. Interdum et malesuada fames ac ante ipsum primis in faucibus.</p>&#10;<a href='/login' class='btn btn_alt'>Login</a>",
            "component": null
          },
          {
            "column": "col-12 col-lg-6",
            "text": null,
            "component": "auth"
          }
        ],
        "background": "assets/videos/trening.mp4"
      }
    ]
}
      `;
    } else if (url === '/login') {
      return `
{
    "type": "section",
    "sections": [
      {
        "metaData": {
          "id": "login",
          "type": "section_se",
          "background": "background_video"
        },
        "headerInfos": {
          "title": "Lorem ipsum",
          "subTitle": "Aenean tempor porta ante non aliquam",
          "insideFirstColumn": true
        },
        "content": [
          {
            "column": "col-12 col-lg-6",
            "text": "<p>Aliquam purusmassa, lobortis non bibendum sed, vestibulum quis arcu. Vestibulum sodales imperdiet mollis. Mauris turpis dolor, gravida vel porttitor dictum, facilisis in eros. Interdum et malesuada fames ac ante ipsum primis in faucibus.</p>&#10;<a href='/register' class='btn btn_alt'>Register</a>",
            "component": null
          },
          {
            "column": "col-12 col-lg-6",
            "text": null,
            "component": "auth"
          }
        ],
        "background": "assets/videos/trening.mp4"
      }
    ]
}
      `;
    } else {
      return '';
    }
  }
}
