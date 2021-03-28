import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';

@Component({
  selector: 'app-alert',
  templateUrl: './alert.component.html',
  styleUrls: ['./alert.component.css']
})
export class AlertComponent implements OnInit {
  @Input() id = '';
  @Input() displayHideButton = true;
  @Input() level = '';
  @Input() header = '';
  @Input() text = '';
  @Input() position = '';
  @Output() hideEvent = new EventEmitter<string>();
  icon = '';

  ngOnInit(): void {
    this.icon = this.returnIcon();
  }

  returnIcon(): string {
    switch (this.level) {
      case 'danger':
        return 'bi-exclamation-octagon';
      case 'warning':
        return 'bi-exclamation-triangle';
      case 'success':
        return 'bi-check-circle';
      default:
        return 'bi-info-square';
    }
  }

  public hideAlert(): void {
    this.hideEvent.emit(this.id);
  }
}
