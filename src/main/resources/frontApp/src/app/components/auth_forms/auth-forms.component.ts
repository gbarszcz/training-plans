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
      info: 'This must be an email. For example: name.surname@example.com',
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
      info: 'Min 8 and max 50 characters. Please use: upper and lower letters, special characters [#?!@$%^&*-+], digits.',
      type: {
        value: 'password',
        error: 'This is not correct password format!'
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
      info: 'This must be equals to password. Min 8 and max 50 characters. Please use: upper and lower letters, special characters [#?!@$%^&*-+], digits.',
      type: {
        value: 'password',
        error: 'This is not correct password format!'
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

  private inputsEvents(): void {
    this.clickPasswordTrigger();
    if (this.formType === 'register') {
      this.liveValidation();
    }
    this.passwordStrength();
  }

  private clickPasswordTrigger(): void {
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

  private liveValidation(): void {
    const BUTTON = document.querySelector('[type="submit"]');
    document.querySelectorAll('input').forEach((input, index) => {
      const INFO_CONTAINER = document.querySelector('#' + input.name + '-validation');

      input.addEventListener('keyup', (event) => {
        if (this.inputsParams[index].required && input.value.length === 0) {
          // @ts-ignore
          this.markInput(INFO_CONTAINER, 'danger', this.inputsParams[index].required.error);
        } else if (this.isNotCorrectType(input.value, index)) {
          // @ts-ignore
          this.markInput(INFO_CONTAINER, 'danger', this.inputsParams[index].type.error);
        } else if (input.value.length > this.inputsParams[index].max.value) {
          // @ts-ignore
          this.markInput(INFO_CONTAINER, 'danger', this.inputsParams[index].max.error);
          // @ts-ignore
        } else if (this.inputsParams[index].name === 'password') {
          const REPEAT_PASSWORD_INPUT = document.querySelector('[name="repeatPassword"]');
          // @ts-ignore
          if (REPEAT_PASSWORD_INPUT.value && REPEAT_PASSWORD_INPUT.value !== input.value) {
            // @ts-ignore
            this.markInput(INFO_CONTAINER, 'danger', null);
            // @ts-ignore
            this.markInput(REPEAT_PASSWORD_INPUT.closest('label'), 'danger', 'This is not equals with password field!');
          } else {
            // @ts-ignore
            this.markInput(INFO_CONTAINER, 'danger', null);
            // @ts-ignore
            this.markInput(REPEAT_PASSWORD_INPUT.closest('label'), 'danger', null);
          }
        } else if (this.inputsParams[index].name === 'repeatPassword') {
          // @ts-ignore
          const PASSWORD_VALUE = document.querySelector('[name="password"]')?.value;
          if (PASSWORD_VALUE && PASSWORD_VALUE !== input.value) {
            // @ts-ignore
            this.markInput(INFO_CONTAINER, 'danger', 'This is not equals with password field!');
          } else {
            // @ts-ignore
            this.markInput(INFO_CONTAINER, 'danger', null);
          }
        } else {
          // @ts-ignore
          this.markInput(INFO_CONTAINER, 'danger', null);
        }

        // @ts-ignore
        this.disableButton(BUTTON);
      });
    });
  }

  private isNotCorrectType(value: string, index: number): boolean {
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

  private markInput(infoContainer: Element, level: string, message: string | null): void {
    const LABEL = infoContainer.closest('label');

    if (LABEL) {
      if (message != null) {
        if (!LABEL.classList.contains(level)) {
          LABEL.classList.add(level);
          // @ts-ignore
          infoContainer.innerHTML = message;
        }
      } else {
        if (LABEL.classList.contains(level)) {
          LABEL.classList.remove(level);
          // @ts-ignore
          infoContainer.innerHTML = '';
        }
      }
    }
  }

  private disableButton(button: Element): void {
    if (document.querySelectorAll('.danger').length > 0) {
      button?.setAttribute('disabled', 'disabled');
    } else {
      for (const input of this.inputsParams) {
        // @ts-ignore
        if (input.required.value && !document.querySelector('[name="' + input.name + '"]')?.value) {
          button?.setAttribute('disabled', 'disabled');
          return;
        }
      }

      button?.removeAttribute('disabled');
    }
  }

  private passwordStrength(): void {
    const PASSWORD_INPUT = document.querySelector('[type="password"]');
    if (PASSWORD_INPUT) {
      const PASSWORD_INFO_CONTAINER = document.querySelector('#password-strength');

      PASSWORD_INPUT.addEventListener('keyup', (e) => {
        // @ts-ignore
        if (PASSWORD_INPUT.value.length > 0) {
          // @ts-ignore
          const LEVEL = this.calculatePasswordStrength(PASSWORD_INPUT.value, 8,
            this.inputsParams.filter((x) => x.name === 'password')[0].max.value);
          // @ts-ignore
          this.displayPasswordStrength(PASSWORD_INFO_CONTAINER, LEVEL);
        } else {
          // @ts-ignore
          this.markPasswordStrength(PASSWORD_INFO_CONTAINER);
        }
      });
    }
  }

  private calculatePasswordStrength(value: string, min: number, max: number): number {
    const LOWERCASES_REGEX = /[a-z]/;
    const UPPERCASES_REGEX = /[A-Z]/;
    const SPECIALCASES_REGEX = /[#?!@$%^&*-+]/;
    const DIGITS_REGEX = /[0-9]/;

    let level = 0;
    if (LOWERCASES_REGEX.test(value)) {
      ++level;
    }
    if (UPPERCASES_REGEX.test(value)) {
      ++level;
    }
    if (DIGITS_REGEX.test(value)) {
      ++level;
    }
    if (new RegExp('^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-+]).{' + min + ',' + max + '}$').test(value)) {
      ++level;
    }
    if (SPECIALCASES_REGEX.test(value)) {
      level += 2;
    }
    level += value.length * min / max;
    level += (value.length - value.replace(DIGITS_REGEX, '').length) * min / max;
    level += (value.length - value.replace(UPPERCASES_REGEX, '').length) * min / max;
    level += (value.length - value.replace(LOWERCASES_REGEX, '').length) * min / max;
    level += (value.length - value.replace(SPECIALCASES_REGEX, '').length) * min / max;
    level += String.prototype.concat(...new Set(value)).length / max;

    console.log(String.prototype.concat(...new Set(value)).length / max);

    return level;
  }

  private displayPasswordStrength(infoContainer: Element, level: number): void {
    console.log(level);
    if (level < 8.25) {
      this.markPasswordStrength(infoContainer, 'danger', 'Weak');
    } else if (level >= 8.25 && level <= 9) {
      this.markPasswordStrength(infoContainer, 'warning', 'Good');
    } else {
      this.markPasswordStrength(infoContainer, 'success', 'Strong');
    }
  }

  private markPasswordStrength(infoContainer: Element, level: string | null, message: string | null): void {
    infoContainer.className = '';

    if (level !== null && message !== null) {
      infoContainer.classList.add(level);
      infoContainer.innerHTML = message;
    }
    else {
      infoContainer.innerHTML = '';
    }
  }
}
