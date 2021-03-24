import {AfterViewInit, Component, Input} from '@angular/core';

@Component({
  selector: 'auth-forms',
  templateUrl: './auth-forms.component.html',
  styleUrls: ['./auth-forms.component.css']
})
export class AuthFormsComponent implements AfterViewInit {
  @Input() formType = 'register';
  inputsParams = [
    {
      placeholder: 'Email',
      name: 'email',
      icon: 'bi-at',
      type: {
        value: 'email',
        error: 'This is not correct email!'
      },
      max: {
        value: 150,
        error: 'Length of this value is to height'
      },
      required: {
        value: true,
        error: 'This is required field'
      },
    },
    {
      placeholder: 'Password',
      name: 'password',
      icon: 'bi-eye',
      type: {
        value: 'password',
        error: 'This is not correct password! Min 8 and max 50 characters. Please use, upper and lower letter, special character [#?!@$%^&*-+], digit'
      },
      max: {
        value: 50,
        error: 'Length of this value is to height'
      },
      required: {
        value: true,
        error: 'This is required field'
      },
    },
    {
      placeholder: 'Repeat password',
      name: 'repeatPassword',
      icon: 'bi-eye',
      type: {
        value: 'password',
        error: 'This is not correct password! Min 8 and max 50 characters. Please use, upper and lower letter, special character [#?!@$%^&*-+], digit'
      },
      max: {
        value: 50,
        error: 'Length of this value is to height'
      },
      required: {
        value: true,
        error: 'This is required field'
      },
    }
  ];

  constructor() {
    if (this.formType !== 'register') {
      this.inputsParams = this.inputsParams.filter((input) => {
        return input.name !== 'repeatPassword';
      });
    }
    this.formType = this.formType.replace('-', ' ');
  }

  ngAfterViewInit(): void {
    this.inputsEvents();
  }

  inputsEvents(): void {
    this.clickPasswordTrigger();
    if (this.formType === 'register') {
      this.liveValidation();
    }
  }

  clickPasswordTrigger(): void {
    const PASSWORD_TYPE_TRIGGERS = document.querySelectorAll('.bi-eye');

    PASSWORD_TYPE_TRIGGERS.forEach((trigger) => {
      const INPUT = trigger.closest('.input-group')?.querySelector('input');

      if (INPUT) {
        trigger.addEventListener('click', e => {
          if (trigger.classList.contains('bi-eye')) {
            trigger.classList.remove('bi-eye');
            trigger.classList.add('bi-eye-slash');
            INPUT.setAttribute('type', 'text');
          } else if (trigger.classList.contains('bi-eye-slash')) {
            trigger.classList.remove('bi-eye-slash');
            trigger.classList.add('bi-eye');
            INPUT.setAttribute('type', 'password');
          }
        });
      }
    });
  }

  liveValidation(): void {
    const BUTTON = document.querySelector('[type="submit"]');
    document.querySelectorAll('input').forEach((input, index) => {
      const LABEL = input.closest('label');

      input.addEventListener('change', (event) => {
        if (this.inputsParams[index].required && input.value.length === 0) {
          // @ts-ignore
          this.markInput(LABEL, 'danger', this.inputs[index].required.error);
        } else if (this.isNotCorrectType(input.value, index)) {
          // @ts-ignore
          this.markInput(LABEL, 'danger', this.inputs[index].type.error);
        } else if (input.value.length > this.inputsParams[index].max.value) {
          // @ts-ignore
          this.markInput(LABEL, 'danger', this.inputs[index].max.error);
          // @ts-ignore
        } else if (this.inputs[index].name === 'password') {
          const REPEAT_PASSWORD_INPUT = document.querySelector('[name="repeatPassword"]');
          // @ts-ignore
          if (REPEAT_PASSWORD_INPUT.value && REPEAT_PASSWORD_INPUT.value !== input.value) {
            // @ts-ignore
            this.markInput(LABEL, 'danger', null);
            // @ts-ignore
            this.markInput(REPEAT_PASSWORD_INPUT.closest('label'), 'danger', 'This is not equals with password field!');
          } else {
            // @ts-ignore
            this.markInput(LABEL, 'danger', null);
            // @ts-ignore
            this.markInput(REPEAT_PASSWORD_INPUT.closest('label'), 'danger', null);
          }
        } else if (this.inputsParams[index].name === 'repeatPassword') {
          // @ts-ignore
          const PASSWORD_VALUE = document.querySelector('[name="password"]')?.value;
          if (PASSWORD_VALUE && PASSWORD_VALUE !== input.value) {
            // @ts-ignore
            this.markInput(LABEL, 'danger', 'This is not equals with password field!');
          } else {
            // @ts-ignore
            this.markInput(LABEL, 'danger', null);
          }
        } else {
          // @ts-ignore
          this.markInput(LABEL, 'danger', null);
        }

        // @ts-ignore
        this.disableButton(BUTTON);
      });
    });
  }

  isNotCorrectType(value: string, index: number): boolean {
    let re;
    switch (this.inputsParams[index].type.value) {
      case 'email':
        re = /\S+@\S+\.\S+/;
        return !re.test(value);
      case 'password':
        re = new RegExp('^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-+]).{8,' + this.inputsParams[index].max.value + '}$');
        return !re.test(value);
      default:
        return (typeof value !== 'string');
    }
  }

  markInput(label: HTMLInputElement, level: string, message: string | null): void {
    if (message != null) {
      if (!label.classList.contains(level)) {
        label.classList.add(level);
        // @ts-ignore
        label.querySelector('.validation').innerHTML = message;
      }
    } else {
      if (label.classList.contains(level)) {
        label.classList.remove(level);
        // @ts-ignore
        label.querySelector('.validation').innerHTML = '';
      }
    }
  }

  disableButton(button: Element): void {
    console.log(document.querySelectorAll('.danger'));
    if (document.querySelectorAll('.danger').length > 0) {
      button?.setAttribute('disabled', 'disabled');
    } else {
      for (const input of this.inputsParams) {
        // @ts-ignore
        if (input.required.value && !document.querySelector('[name="' + input.name + '"]')?.value)
        {
          button?.setAttribute('disabled', 'disabled');
          return;
        }
      }

      button?.removeAttribute('disabled');
    }
  }
}
