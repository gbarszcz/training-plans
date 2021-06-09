import {Component, EventEmitter, Input, Output} from '@angular/core';
import {IAlert} from '../../models/IAlert';

@Component({
  selector: 'app-alert',
  templateUrl: './alert.component.html',
  styleUrls: ['./alert.component.css']
})
export class AlertComponent {
  @Input() alert: IAlert | null = null;
  @Input() position = '';
  @Output() hideEvent = new EventEmitter<string>();
  icon = new Map([
    ['danger', 'bi-exclamation-octagon'],
    ['warning', 'bi-exclamation-triangle'],
    ['success', 'bi-check-circle'],
    ['info', 'bi-info-square'],
  ]);

  public hideAlert(): void {
    if (this.alert !== null) {
      this.hideEvent.emit(this.alert.id.toString());
    } else {
      this.hideEvent.emit('-1');
    }
  }
}
