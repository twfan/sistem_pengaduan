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
import java.lang.IllegalStateException;
import java.lang.Override;

public class AduanActivity_ViewBinding implements Unbinder {
  private AduanActivity target;

  @UiThread
  public AduanActivity_ViewBinding(AduanActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public AduanActivity_ViewBinding(AduanActivity target, View source) {
    this.target = target;

    target.ripKirimMasalah = Utils.findRequiredViewAsType(source, R.id.ripKirimMasalah, "field 'ripKirimMasalah'", RippleView.class);
    target.editMasalah = Utils.findRequiredViewAsType(source, R.id.editMasalah, "field 'editMasalah'", EditText.class);
    target.btnKirimMasalah = Utils.findRequiredViewAsType(source, R.id.btnKirimMasalah, "field 'btnKirimMasalah'", Button.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    AduanActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ripKirimMasalah = null;
    target.editMasalah = null;
    target.btnKirimMasalah = null;
  }
}
