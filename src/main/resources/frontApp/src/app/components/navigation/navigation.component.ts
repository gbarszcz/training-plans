import {Component, Input} from '@angular/core';

import {Navigation} from '../../utils/Navigation';

@Component({
  selector: 'main-navigation',
  templateUrl: './navigation.component.html'
})

export class NavigationComponent {
  appName = 'TC4U';
  @Input() navigation: Navigation | null = null;
}
