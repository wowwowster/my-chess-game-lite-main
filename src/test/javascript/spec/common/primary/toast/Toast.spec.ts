import { shallowMount, VueWrapper } from '@vue/test-utils';
import { ToastVue } from '@/common/primary/toast';
import sinon, { SinonStub } from 'sinon';
import { ToastType } from '@/common/primary/toast/ToastType';
import { AlertListener } from '@/common/domain/alert/AlertListener';
import { AlertListenerFixture, stubAlertListener } from '../../domain/AlertListener.fixure';
import { TimeoutListener } from '@/common/primary/timeout/Timeout';
import { stubTimeout } from '../timeout/Timeout.fixture';
import { describe, it, expect } from 'vitest';

let wrapper: VueWrapper;
let component: any;

interface WrapperOptions {
  alertListener: AlertListener;
  toastTimeout: TimeoutListener;
}

interface OpenToastOptions extends Partial<WrapperOptions> {
  message?: string;
}

const wrap = (wrapperOptions?: Partial<WrapperOptions>) => {
  const { alertListener, toastTimeout }: WrapperOptions = {
    alertListener: stubAlertListener(),
    toastTimeout: stubTimeout(),
    ...wrapperOptions,
  };
  wrapper = shallowMount(ToastVue, {
    global: {
      provide: {
        alertListener,
        timeout: () => toastTimeout,
      },
    },
  });
  component = wrapper.vm;
};

const openToast =
  (listen: (alertListener: AlertListenerFixture) => SinonStub) =>
  async (wrapperOptions: OpenToastOptions = {}): Promise<void> => {
    const alertListener = stubAlertListener();
    const message = wrapperOptions.message ?? 'message';
    wrap({ alertListener, ...wrapperOptions });
    const [callback] = listen(alertListener).getCall(0).args;

    callback(message);

    await wrapper.vm.$nextTick();
  };

const errorToast = openToast(alertListener => alertListener.onError);

const successToast = openToast(alertListener => alertListener.onSuccess);

const WORD_BY_MIN = 200;
const MIN_IN_MS = 1000 * 60;
const wordsToMs = (words: number) => (words * MIN_IN_MS) / WORD_BY_MIN;
const TOAST_ATTENTION_MS = 1500;

describe('Toast', () => {
  it('should exist', () => {
    wrap();

    expect(wrapper.exists()).toBe(true);
  });

  it('should display toast on success', async () => {
    await successToast();

    expect(component.message).toBe('message');
    expect(component.type).toBe(ToastType.SUCCESS);
  });

  it('should display toast on error', async () => {
    await errorToast();

    expect(component.message).toBe('message');
    expect(component.type).toBe(ToastType.ERROR);
  });

  it('should hide toast on close', async () => {
    await successToast();

    await wrapper.find('[data-selector="toast.close"]').trigger('click');

    expect(wrapper.find('[data-selector="toast-overlay"]').exists()).toBe(false);
  });

  it('should unsubscribe on before unmount', () => {
    const alertListener = stubAlertListener();
    const unsubscribeSuccess = sinon.stub();
    alertListener.onSuccess.returns(unsubscribeSuccess);
    const unsubscribeError = sinon.stub();
    alertListener.onError.returns(unsubscribeError);
    wrap({ alertListener });

    wrapper.unmount();

    expect(unsubscribeSuccess.calledOnce).toBe(true);
    expect(unsubscribeError.calledOnce).toBe(true);
  });

  describe('Timeout', () => {
    it('should start timeout after display', async () => {
      const toastTimeout = stubTimeout();
      await successToast({
        toastTimeout,
      });

      expect(component.show).toBe(true);
      expect(toastTimeout.register.callCount).toBe(1);
      expect(toastTimeout.unregister.callCount).toBe(0);
    });

    it('should hide after timeout', async () => {
      const toastTimeout = stubTimeout();
      await successToast({
        toastTimeout,
      });
      const [timeoutCall] = toastTimeout.register.getCall(0).args;

      timeoutCall();

      expect(component.show).toBe(false);
      expect(toastTimeout.register.callCount).toBe(1);
      expect(toastTimeout.unregister.callCount).toBe(1);
    });

    it.each([
      [wordsToMs(3) + TOAST_ATTENTION_MS, 'one two three'],
      [wordsToMs(12) + TOAST_ATTENTION_MS, 'one two three four five six seven   eight nine ten eleven\ttwelve'],
    ])('should be visible for %i ms with "%s"', async (ms, message) => {
      const toastTimeout = stubTimeout();
      await successToast({
        toastTimeout,
        message,
      });

      const [, milliseconds] = toastTimeout.register.getCall(0).args;

      expect(milliseconds).toBe(ms);
    });

    it('should cancel timeout on close', async () => {
      const toastTimeout = stubTimeout();
      await successToast({
        toastTimeout,
      });

      await wrapper.find('[data-selector="toast.close"]').trigger('click');

      expect(component.show).toBe(false);
      expect(toastTimeout.unregister.callCount).toBe(1);
    });
  });
});
