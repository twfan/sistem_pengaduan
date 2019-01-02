// Generated code from Butter Knife. Do not modify!
package e.taufan.sistempengaduan;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.andexert.library.RippleView;
import com.weiwangcn.betterspinner.library.BetterSpinner;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MainActivity_ViewBinding implements Unbinder {
  private MainActivity target;

  @UiThread
  public MainActivity_ViewBinding(MainActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MainActivity_ViewBinding(MainActivity target, View source) {
    this.target = target;

    target.SpinnerDocType = Utils.findRequiredViewAsType(source, R.id.SpinnerDocType, "field 'SpinnerDocType'", BetterSpinner.class);
    target.editTextNoOnline = Utils.findRequiredViewAsType(source, R.id.editTextNoOnline, "field 'editTextNoOnline'", EditText.class);
    target.btnLogin = Utils.findRequiredViewAsType(source, R.id.btnLogin, "field 'btnLogin'", Button.class);
    target.ripLogin = Utils.findRequiredViewAsType(source, R.id.ripLogin, "field 'ripLogin'", RippleView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    MainActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.SpinnerDocType = null;
    target.editTextNoOnline = null;
    target.btnLogin = null;
    target.ripLogin = null;
  }
}
