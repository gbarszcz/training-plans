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
    NAV.mainNav = this.ifItemDisabledUpdateUpdateItem(NAV.mainNav);
    NAV.subNav = this.ifItemDisabledUpdateUpdateItem(NAV.subNav);

    this.currentRoute = currentRoute;
    this.brandItem = NAV.brandItem;
    this.leftNavItems = NAV.mainNav.filter((navItem: INavigationItem) => navItem.left);
    this.rightNavItems = NAV.mainNav.filter((navItem: INavigationItem) => !navItem.left);
    this.secNavItems = NAV.subNav;
  }

  private ifItemDisabledUpdateUpdateItem(navItems: INavigationItem[]): INavigationItem[] {
    navItems.forEach((item) => {
      if (item.disabled) {
        item.link = '';
        item.subItems = [];
      }
    });
    return navItems;
  }

  public createNavItemContent(item: IStringItem[]): string {
    const ICON_COMPONENTS = new Map([
      ['start', '<i class="bi '],
      ['end', '"></i> ']
    ]);
    const IMG_COMPONENTS = new Map([
      ['start', '<img src="'],
      ['end', '">']
    ]);
    let html = '';

    item.forEach((content) => {
      switch (content.type) {
        case EStringItemType.i: {
          html += ICON_COMPONENTS.get('start') + content.value + ICON_COMPONENTS.get('end');
          break;
        }
        case EStringItemType.img: {
          html += IMG_COMPONENTS.get('start') + content.value + IMG_COMPONENTS.get('end');
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
