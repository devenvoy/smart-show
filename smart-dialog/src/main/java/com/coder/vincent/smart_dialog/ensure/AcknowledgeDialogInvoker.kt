package com.coder.vincent.smart_dialog.ensure

import android.app.Activity
import android.app.Dialog
import android.content.DialogInterface
import androidx.`annotation`.ColorInt
import androidx.`annotation`.StringRes
import com.coder.vincent.series.common_lib.bean.TextStyle
import com.coder.vincent.series.common_lib.canShowDialog
import com.coder.vincent.series.common_lib.resourceToString
import com.coder.vincent.smart_dialog.CancelBtnListener
import com.coder.vincent.smart_dialog.ConfirmBtnListener
import kotlin.Boolean
import kotlin.Float
import kotlin.Int
import kotlin.String

internal class AcknowledgeDialogInvoker : AcknowledgeDialogFacade.Builder, AcknowledgeDialogFacade.Handle {
  private val config: AcknowledgeDialog.Config = AcknowledgeDialog.Config()

  private val updater: AcknowledgeDialogFacade.Updater = InnerClass()

  private val dialogFactory: AcknowledgeDialogFactory = AcknowledgeDialogFactory()

  private var dialog: Dialog? = null

  override fun show() {
    kotlin.runCatching { if (dialog?.ownerActivity?.canShowDialog() == true) dialog?.show() }
  }

  override fun dismiss() {
    if (isShowing()) {
      kotlin.runCatching { dialog?.dismiss() }
    }
  }

  override fun cancel() {
    dialog?.cancel()
  }

  override fun isShowing(): Boolean = dialog?.isShowing == true

  override fun build(activity: Activity): AcknowledgeDialogFacade.Handle {
    if (!activity.canShowDialog()) {
      return this
    }
    dialog = dialogFactory.produceDialog(activity, config)
    dialog?.setOwnerActivity(activity)
    employConfig()
    return this
  }

  override fun updater(): AcknowledgeDialogFacade.Updater = updater

  override fun dimBehind(dim: Boolean): AcknowledgeDialogFacade.Builder = this.apply {
    config.dimBehind.update(value = dim, employ = false)
  }

  override fun cancelable(cancelable: Boolean): AcknowledgeDialogFacade.Builder = this.apply {
    config.cancelable.update(value = cancelable, employ = false)
  }

  override fun cancelOnTouchOutside(cancelOnTouchOutside: Boolean): AcknowledgeDialogFacade.Builder =
      this.apply {
    config.cancelOnTouchOutside.update(value = cancelOnTouchOutside, employ = false)
  }

  override fun dialogShowListener(onShowListener: DialogInterface.OnShowListener):
      AcknowledgeDialogFacade.Builder = this.apply {
    config.dialogShowListener.update(value = onShowListener, employ = false)
  }

  override fun dialogDismissListener(onDismissListener: DialogInterface.OnDismissListener):
      AcknowledgeDialogFacade.Builder = this.apply {
    config.dialogDismissListener.update(value = onDismissListener, employ = false)
  }

  override fun dialogCancelListener(onCancelListener: DialogInterface.OnCancelListener):
      AcknowledgeDialogFacade.Builder = this.apply {
    config.dialogCancelListener.update(value = onCancelListener, employ = false)
  }

  override fun title(title: String): AcknowledgeDialogFacade.Builder = this.apply {
          config.title.update(value = title, employ = false)
  }

  override fun titleResource(@StringRes titleResource: Int): AcknowledgeDialogFacade.Builder =
      this.apply {
          config.title.update(value = titleResource.resourceToString(), employ = false)
  }

  override fun titleStyle(
    @ColorInt color: Int,
    size: Float,
    bold: Boolean,
  ): AcknowledgeDialogFacade.Builder = this.apply {
          config.titleStyle.update(value = TextStyle(color, size, bold), employ = false)
  }

  override fun message(message: String): AcknowledgeDialogFacade.Builder = this.apply {
          config.message.update(value = message, employ = false)
  }

  override fun messageResource(@StringRes messageResource: Int): AcknowledgeDialogFacade.Builder =
      this.apply {
          config.message.update(value = messageResource.resourceToString(), employ = false)
  }

  override fun messageStyle(
    @ColorInt color: Int,
    size: Float,
    bold: Boolean,
  ): AcknowledgeDialogFacade.Builder = this.apply {
          config.messageStyle.update(value = TextStyle(color, size, bold), employ = false)
  }

  override fun confirmBtnLabel(confirmBtnLabel: String): AcknowledgeDialogFacade.Builder = this.apply {
          config.confirmBtnLabel.update(value = confirmBtnLabel, employ = false)
  }

  override fun confirmBtnLabelResource(@StringRes confirmBtnLabelResource: Int):
      AcknowledgeDialogFacade.Builder = this.apply {
          config.confirmBtnLabel.update(value = confirmBtnLabelResource.resourceToString(), employ =
        false)
  }

  override fun confirmBtnLabelStyle(
    @ColorInt color: Int,
    size: Float,
    bold: Boolean,
  ): AcknowledgeDialogFacade.Builder = this.apply {
          config.confirmBtnLabelStyle.update(value = TextStyle(color, size, bold), employ = false)
  }

  override fun confirmBtnListener(confirmBtnListener: ConfirmBtnListener):
      AcknowledgeDialogFacade.Builder = this.apply {
          config.confirmBtnListener.update(value = confirmBtnListener, employ = false)
  }

  override fun delayToConfirm(delayToConfirm: Int): AcknowledgeDialogFacade.Builder = this.apply {
          config.delayToConfirm.update(value = delayToConfirm, employ = false)
  }

  override fun cancelBtnLabel(cancelBtnLabel: String): AcknowledgeDialogFacade.Builder = this.apply {
          config.cancelBtnLabel.update(value = cancelBtnLabel, employ = false)
  }

  override fun cancelBtnLabelResource(@StringRes cancelBtnLabelResource: Int):
      AcknowledgeDialogFacade.Builder = this.apply {
          config.cancelBtnLabel.update(value = cancelBtnLabelResource.resourceToString(), employ =
        false)
  }

  override fun cancelBtnLabelStyle(
    @ColorInt color: Int,
    size: Float,
    bold: Boolean,
  ): AcknowledgeDialogFacade.Builder = this.apply {
          config.cancelBtnLabelStyle.update(value = TextStyle(color, size, bold), employ = false)
  }

  override fun cancelBtnListener(cancelBtnListener: CancelBtnListener):
      AcknowledgeDialogFacade.Builder = this.apply {
          config.cancelBtnListener.update(value = cancelBtnListener, employ = false)
  }

  private fun employConfig() {
    config.dimBehind.employIfChanged()
    config.cancelable.employIfChanged()
    config.cancelOnTouchOutside.employIfChanged()
    config.dialogShowListener.employIfChanged()
    config.dialogDismissListener.employIfChanged()
    config.dialogCancelListener.employIfChanged()
    config.title.employIfChanged()
    config.titleStyle.employIfChanged()
    config.message.employIfChanged()
    config.messageStyle.employIfChanged()
    config.confirmBtnLabel.employIfChanged()
    config.confirmBtnLabelStyle.employIfChanged()
    config.confirmBtnListener.employIfChanged()
    config.delayToConfirm.employIfChanged()
    config.cancelBtnLabel.employIfChanged()
    config.cancelBtnLabelStyle.employIfChanged()
    config.cancelBtnListener.employIfChanged()
  }

  private inner class InnerClass : AcknowledgeDialogFacade.Updater {
    override fun dimBehind(dim: Boolean): AcknowledgeDialogFacade.Updater = this.apply {
      config.dimBehind.update(value = dim, employ = false)
    }

    override fun cancelable(cancelable: Boolean): AcknowledgeDialogFacade.Updater = this.apply {
      config.cancelable.update(value = cancelable, employ = false)
    }

    override fun cancelOnTouchOutside(cancelOnTouchOutside: Boolean): AcknowledgeDialogFacade.Updater =
        this.apply {
      config.cancelOnTouchOutside.update(value = cancelOnTouchOutside, employ = false)
    }

    override fun dialogShowListener(onShowListener: DialogInterface.OnShowListener):
        AcknowledgeDialogFacade.Updater = this.apply {
      config.dialogShowListener.update(value = onShowListener, employ = false)
    }

    override fun dialogDismissListener(onDismissListener: DialogInterface.OnDismissListener):
        AcknowledgeDialogFacade.Updater = this.apply {
      config.dialogDismissListener.update(value = onDismissListener, employ = false)
    }

    override fun dialogCancelListener(onCancelListener: DialogInterface.OnCancelListener):
        AcknowledgeDialogFacade.Updater = this.apply {
      config.dialogCancelListener.update(value = onCancelListener, employ = false)
    }

    override fun title(title: String): AcknowledgeDialogFacade.Updater = this.apply {
            config.title.update(value = title, employ = false)
    }

    override fun titleResource(@StringRes titleResource: Int): AcknowledgeDialogFacade.Updater =
        this.apply {
            config.title.update(value = titleResource.resourceToString(), employ = false)
    }

    override fun titleStyle(
      @ColorInt color: Int,
      size: Float,
      bold: Boolean,
    ): AcknowledgeDialogFacade.Updater = this.apply {
            config.titleStyle.update(value = TextStyle(color, size, bold), employ = false)
    }

    override fun message(message: String): AcknowledgeDialogFacade.Updater = this.apply {
            config.message.update(value = message, employ = false)
    }

    override fun messageResource(@StringRes messageResource: Int): AcknowledgeDialogFacade.Updater =
        this.apply {
            config.message.update(value = messageResource.resourceToString(), employ = false)
    }

    override fun messageStyle(
      @ColorInt color: Int,
      size: Float,
      bold: Boolean,
    ): AcknowledgeDialogFacade.Updater = this.apply {
            config.messageStyle.update(value = TextStyle(color, size, bold), employ = false)
    }

    override fun confirmBtnLabel(confirmBtnLabel: String): AcknowledgeDialogFacade.Updater = this.apply {
            config.confirmBtnLabel.update(value = confirmBtnLabel, employ = false)
    }

    override fun confirmBtnLabelResource(@StringRes confirmBtnLabelResource: Int):
        AcknowledgeDialogFacade.Updater = this.apply {
            config.confirmBtnLabel.update(value = confirmBtnLabelResource.resourceToString(), employ
          = false)
    }

    override fun confirmBtnLabelStyle(
      @ColorInt color: Int,
      size: Float,
      bold: Boolean,
    ): AcknowledgeDialogFacade.Updater = this.apply {
            config.confirmBtnLabelStyle.update(value = TextStyle(color, size, bold), employ = false)
    }

    override fun confirmBtnListener(confirmBtnListener: ConfirmBtnListener):
        AcknowledgeDialogFacade.Updater = this.apply {
            config.confirmBtnListener.update(value = confirmBtnListener, employ = false)
    }

    override fun delayToConfirm(delayToConfirm: Int): AcknowledgeDialogFacade.Updater = this.apply {
            config.delayToConfirm.update(value = delayToConfirm, employ = false)
    }

    override fun cancelBtnLabel(cancelBtnLabel: String): AcknowledgeDialogFacade.Updater = this.apply {
            config.cancelBtnLabel.update(value = cancelBtnLabel, employ = false)
    }

    override fun cancelBtnLabelResource(@StringRes cancelBtnLabelResource: Int):
        AcknowledgeDialogFacade.Updater = this.apply {
            config.cancelBtnLabel.update(value = cancelBtnLabelResource.resourceToString(), employ =
          false)
    }

    override fun cancelBtnLabelStyle(
      @ColorInt color: Int,
      size: Float,
      bold: Boolean,
    ): AcknowledgeDialogFacade.Updater = this.apply {
            config.cancelBtnLabelStyle.update(value = TextStyle(color, size, bold), employ = false)
    }

    override fun cancelBtnListener(cancelBtnListener: CancelBtnListener):
        AcknowledgeDialogFacade.Updater = this.apply {
            config.cancelBtnListener.update(value = cancelBtnListener, employ = false)
    }

    override fun commit(): AcknowledgeDialogFacade.Handle {
      employConfig()
      return this@AcknowledgeDialogInvoker
    }
  }
}
