import {Component, Input, OnChanges} from '@angular/core';
import {DatePipe} from '@angular/common';
import {IAlert} from '../../models/IAlert';
import {AppService} from '../../app.service';

@Component({
  selector: 'user-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnChanges {
  @Input() response: any | null = null;
  userName = 'Undefined';
  formData: any = {};
  userData: any = {
    user: [
      {
        value: '',
        icon: 'bi-person',
        placeholder: 'First name',
        name: 'firstName',
        type: 'text',
        error: ''
      },
      {
        value: '',
        icon: 'bi-person',
        placeholder: 'Last name',
        name: 'lastName',
        type: 'text',
        error: ''
      },
      {
        value: '',
        icon: 'bi-person-badge',
        require: true,
        placeholder: 'Identifier',
        name: 'identifier',
        type: 'text',
        error: ''
      },
    ],
    about: [
      {
        value: '',
        icon: 'bi-at',
        require: true,
        placeholder: 'Email',
        name: 'email',
        type: 'email',
        error: ''
      },
      {
        value: '',
        icon: 'bi-calendar-event',
        placeholder: 'Birthdate',
        name: 'birthdate',
        type: 'date',
        error: ''
      },
      {
        value: '',
        text: 'About you',
        placeholder: 'About you',
        name: 'describe',
        type: 'textarea',
        error: ''
      },
    ]
  };
  alerts: IAlert[] = [];

  constructor(private datePipe: DatePipe, private service: AppService) {
  }

  ngOnChanges(): void {
    this.response.createdAt = this.dateFormate(this.response?.createdAt);
    this.response.updatedAt = this.dateFormate(this.response?.updatedAt);
    this.userName = this.setUserName();
  }

  put(): void {
    this.service.apiPutRequest('profile', this.formData)
      .subscribe(
        (res: any) => {
          this.alerts.push({
            id: this.alerts.length,
            show: true,
            header: 'Ok!',
            text: 'Your changes have been saved',
            level: 'success',
            displayHideButton: true
          });
        },
        (error: any) => {
          if (error.status === 400) {
            this.prepareErrorFields(error.error.errors);
          } else {
            this.alerts.push({
              id: this.alerts.length,
              show: true,
              header: 'Sorry! We have encountered a problem...',
              text: 'Please try again later :\'(',
              level: 'danger',
              displayHideButton: true
            });
          }
        }
      );
  }

  returnIfHasKay(obj: any, key: string): any | boolean {
    return key in obj ? obj[key] : false;
  }

  private prepareErrorFields(errors: any[]): void {
    ['user', 'about'].forEach((variant: string) => {
      errors.forEach((error) => {
        this.userData[variant.toString()].filter((input: any) => {
          return input.name === error.field;
        })[0].error = error.message;
      });
    });
  }

  private dateFormate(dateString: string | null): string {
    if (!!dateString) {
      return this.datePipe.transform(
        new Date(dateString),
        'MMMM d, y'
      )?.toString() || 'Undefined';
    }
    return 'Undefined';
  }

  private setUserName(): string {
    let firstName = 'firstName' in this.response ? this.response.firstName : '';
    firstName = 'lastName' in this.response.lastName ? (
      firstName !== '' ? `${firstName} ${this.response.lastName})` : this.response.lastName
    ) : '';
    if (!!firstName) {
      return firstName;
    }
    firstName = this.response.identifier || 'Undefined';
    return firstName;
  }
}
