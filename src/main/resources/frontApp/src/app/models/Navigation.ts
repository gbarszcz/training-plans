import {IStringItem} from './IStringItem';
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
  readonly brandItem: IStringItem;
  readonly leftNavItems: INavigationItem[];
  readonly rightNavItems: INavigationItem[];
  readonly secNavItems: INavigationItem[];
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
    this.leftNavItems = NAV.mainNav.filter((navItem: INavigationItem) => navItem.left);
    this.rightNavItems = NAV.mainNav.filter((navItem: INavigationItem) => !navItem.left);
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

  public createNavItemContent(item: IStringItem[]): string {
    let html = '';

    item.forEach((content) => {
      switch (content.type) {
        case EStringItemType.i: {
          html += '<i class="bi ' + content.value + '"></i> ';
          break;
        }
        case EStringItemType.img: {
          html += '<img src="' + content.value + '">';
          break;
        }
        default: {
          html += content.value;
        }
      }
    });

    return html;
  }
}
