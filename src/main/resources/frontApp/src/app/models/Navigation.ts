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
  readonly account: INavigationItem;
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

    this.currentRoute = currentRoute;
    this.brandItem = NAV.brandItem;
    this.account = NAV.mainNav.filter((navItem: INavigationItem) => {
      return navItem.content.filter(item => {
        return item.value === 'Account';
      }).length > 0;
    })[0];
    this.navItems = NAV.mainNav.filter((navItem: INavigationItem) => navItem.link !== this.account.link);
    this.secNavItems = NAV.subNav;
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
