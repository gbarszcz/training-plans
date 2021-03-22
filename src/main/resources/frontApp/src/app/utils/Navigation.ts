import {IStringItem} from '../models/IStringItem';
import {EStringItemType} from '../enums/EStringItemType';

interface INavigationItem {
  content: IStringItem[];
  subItems: INavigationItem[];
  link: string;
  disabled: boolean;
  divider: boolean;
  left: boolean;
}

export class Navigation {
  private brandItem: string;
  private leftNavItems: string[];
  private rightNavItems: string[];
  private secNavItems: string[];
  private currentRoute: string;

  private NAV_LINK = 'nav-link';
  private DROPDOWN_LINK = 'dropdown-item';
  private LINK_COMPONENTS = new Map([
    ['start', '<a class="'],
    ['href', '" href="'],
    ['close', '">'],
    ['end', '</a>']
  ]);
  private ICON_COMPONENTS = new Map([
    ['start', '<i class="bi '],
    ['end', '"></i> ']
  ]);
  private IMG_COMPONENTS = new Map([
    ['start', '<img src="'],
    ['end', '">']
  ]);


  /**
   * param: navJSON @string
   * json format {
   *    brandItem: IStringItem[]
   *    mainNav: INavigationItem[];
   *    subNav: INavigationItem[];
   * }
   * */
  constructor(navJSON: string, currentRoute: string) {
    const nav = JSON.parse(navJSON.replace(currentRoute, '#top'));

    this.currentRoute = currentRoute;
    this.leftNavItems = this.prepareNavItems(nav.mainNav.filter(
      (navItem: INavigationItem) => navItem.left
    ), this.NAV_LINK);
    this.rightNavItems = this.prepareNavItems(nav.mainNav.filter(
      (navItem: INavigationItem) => !navItem.left
    ), this.NAV_LINK);
    this.secNavItems = this.prepareNavItems(nav.subNav, this.NAV_LINK);
    this.brandItem = this.prepareBrandItem(nav.brandItem, nav.mainNav[0].link);
  }

  public getCurrentRoute(): string {
    return this.currentRoute;
  }

  public getBrandItem(): string {
    return this.brandItem;
  }

  public getLeftNavItems(): string[] {
    return this.leftNavItems;
  }

  public getRightNavItems(): string[] {
    return this.rightNavItems;
  }

  public getSecNavItems(): string[] {
    return this.secNavItems;
  }

  private prepareBrandItem(brandItem: IStringItem[], homeURL: string): string {
    const item: INavigationItem = {
      content: brandItem,
      subItems: [],
      link: homeURL,
      disabled: false,
      divider: false,
      left: true,
    };
    return this.prepareNavItem('navbar-brand', item);
  }

  private prepareNavItems(navItems: INavigationItem[], linkCSSClass: string): string[] {
    const items: string[] = [];

    navItems.forEach((item) => {
      items.push(this.prepareNavItem(linkCSSClass, item));
    });

    return items;
  }

  private prepareNavItem(linkCSSClass: string, item: INavigationItem): string {
    let html = (this.LINK_COMPONENTS.get('start') || '') + linkCSSClass;

    if (item.disabled) {
      html += ' disabled';
      item.link = '';
      item.subItems = [];
    }
    if (item.link) {
      html += this.LINK_COMPONENTS.get('href') + item.link;
    }
    if (item.subItems.length > 0) {
      html = '<div class="dropdown">' + html.replace(linkCSSClass, linkCSSClass + ' dropdown-toggle');
      html += this.LINK_COMPONENTS.get('close') + this.createLinkContent(item.content) + this.LINK_COMPONENTS.get('end');
      html += this.createDropdown(item.subItems);
      html += '</div>';
    }
    else {
      // id="navbarDropdown" role="button" data-toggle="dropdown"
      html += this.LINK_COMPONENTS.get('close') + this.createLinkContent(item.content) + this.LINK_COMPONENTS.get('end');
    }
    return html;
  }

  private createLinkContent(contentArr: IStringItem[]): string {
    let html = '';

    contentArr.forEach((content) => {
      html += this.createLinkContentForItem(content);
    });

    return html;
  }

  private createLinkContentForItem(content: IStringItem): string {
    let html = '';

    switch (content.type) {
      case EStringItemType.i: {
        html += this.ICON_COMPONENTS.get('start') + content.value + this.ICON_COMPONENTS.get('end');
        break;
      }
      case EStringItemType.img: {
        html += this.IMG_COMPONENTS.get('start') + content.value + this.IMG_COMPONENTS.get('end');
        break;
      }
      default: {
        html += content.value;
      }
    }

    return html;
  }

  private createDropdown(subItems: INavigationItem[]): string {
    let html = '<div class="dropdown-menu" aria-labelledby="navbarDropdown">';

    subItems.forEach((item) => {
      if (item.divider) {
        html += '<div class="dropdown-divider"></div>';
      }

      html += this.prepareNavItems([item], this.DROPDOWN_LINK);
    });

    return html += '</div>';
  }
}
