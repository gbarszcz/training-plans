import {IStringItem} from './IStringItem';
import {EStringItemType} from '../enums/EStringItemType';

interface INavigationItem {
  content: IStringItem[];
  subItems: INavigationItem[];
  link: string;
  disabled: boolean;
  divider: boolean;
}

export class Navigation {
  readonly brandItem: IStringItem;
  readonly navItems: INavigationItem[];
  readonly secNavItems: INavigationItem[];
  readonly account: INavigationItem | null;
  readonly currentRoute: string;

  /**
   * param: navJSON @string
   * json format {
   *    brandItem: IStringItem
   *    mainNav: INavigationItem[];
   *    subNav: INavigationItem[];
   * }
   * */
  constructor(navJSON: string, currentRoute: string) {
    const NAV = JSON.parse(navJSON);
    NAV.mainNav = this.updateIfItemDisabled(NAV.mainNav);
    NAV.subNav = this.updateIfItemDisabled(NAV.subNav);

    this.currentRoute = `/${currentRoute}`;
    this.brandItem = NAV.brandItem;
    this.account = this.getAccountItem(NAV);
    this.navItems = this.getMainNavItem(NAV);
    this.secNavItems = NAV.subNav;
  }

  private getAccountItem(nav: any): INavigationItem | null {
    return nav.mainNav.filter((navItem: INavigationItem) => {
      return navItem.content.filter(item => {
        return item.value === 'Account';
      }).length;
    })[0] || null;
  }

  private getMainNavItem(nav: any): INavigationItem[] {
    return nav.mainNav.filter((navItem: INavigationItem) => {
      if (this.account) {
        return navItem.link !== this.account.link;
      }
      return true;
    });
  }

  private updateIfItemDisabled(navItems: INavigationItem[]): INavigationItem[] {
    navItems.forEach((item) => {
      if (item.disabled) {
        item.link = '';
        item.subItems = [];
      }
    });
    return navItems;
  }

  public createNavItemContent(items: IStringItem[] | undefined): string {
    let html = '';

    if (typeof items !== 'undefined') {
      items.forEach((content) => {
        switch (content.type) {
          case EStringItemType.i: {
            html += `<i class="bi ${content.value}"></i> `;
            break;
          }
          case EStringItemType.img: {
            html += `<img src="${content.value}"> `;
            break;
          }
          default: {
            html += `<span>${content.value}</span> `;
          }
        }
      });
    }
    return html;
  }
}
