// Generated code from Butter Knife. Do not modify!
package e.taufan.sistempengaduan;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.andexert.library.RippleView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class TanggapanActivity_ViewBinding implements Unbinder {
  private TanggapanActivity target;

  @UiThread
  public TanggapanActivity_ViewBinding(TanggapanActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public TanggapanActivity_ViewBinding(TanggapanActivity target, View source) {
    this.target = target;

    target.ripTampilkanSolusi = Utils.findRequiredViewAsType(source, R.id.ripTampilkanSolusi, "field 'ripTampilkanSolusi'", RippleView.class);
    target.ripTutupAplikasi = Utils.findRequiredViewAsType(source, R.id.ripTutupAplikasi, "field 'ripTutupAplikasi'", RippleView.class);
    target.btnTampilSolusi = Utils.findRequiredViewAsType(source, R.id.btnTampilSolusi, "field 'btnTampilSolusi'", Button.class);
    target.TxtTanggapan = Utils.findRequiredViewAsType(source, R.id.TxtTanggapan, "field 'TxtTanggapan'", TextView.class);
    target.btnTutupAplikasi = Utils.findRequiredViewAsType(source, R.id.btnTutupAplikasi, "field 'btnTutupAplikasi'", Button.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    TanggapanActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ripTampilkanSolusi = null;
    target.ripTutupAplikasi = null;
    target.btnTampilSolusi = null;
    target.TxtTanggapan = null;
    target.btnTutupAplikasi = null;
  }
}
