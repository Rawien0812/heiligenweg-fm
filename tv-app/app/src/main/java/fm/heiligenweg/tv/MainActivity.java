/*
 * Heiligenweg FM tv-app
 * © 2026 Rawien Paltoe. Alle rechten voorbehouden.
 *
 * Opent de radio in tv-modus, schermvullend en met geluid (een app mag direct afspelen,
 * zonder eerst te tikken). Bediening met de afstandsbediening van Fire TV / Android TV.
 */
package fm.heiligenweg.tv;

import android.app.Activity;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class MainActivity extends Activity {

    private static final String RADIO = "https://rawien0812.github.io/heiligenweg-fm/?app=tv#tv";
    private static final String HOST = "rawien0812.github.io";
    private WebView web;
    private long laatsteTerug = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON | WindowManager.LayoutParams.FLAG_FULLSCREEN);

        web = new WebView(this);
        web.setBackgroundColor(Color.BLACK);
        web.setFocusable(true);
        web.setFocusableInTouchMode(true);

        WebSettings s = web.getSettings();
        s.setJavaScriptEnabled(true);
        s.setDomStorageEnabled(true);                    // onthoudt volume, smaak en blokken
        s.setMediaPlaybackRequiresUserGesture(false);    // direct met geluid starten
        s.setLoadWithOverviewMode(true);
        s.setUseWideViewPort(true);
        s.setCacheMode(WebSettings.LOAD_DEFAULT);
        s.setUserAgentString(s.getUserAgentString() + " HeiligenwegFM-TV/1.0");

        web.setWebChromeClient(new WebChromeClient());
        web.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView v, WebResourceRequest req) {
                Uri u = req.getUrl();
                String h = u.getHost() == null ? "" : u.getHost();
                // Alleen de eigen radio en YouTube binnen de app; andere links negeren (geen browser op tv).
                return !(h.equals(HOST) || h.endsWith("youtube.com") || h.endsWith("youtube-nocookie.com") || h.endsWith("google.com") || h.endsWith("gstatic.com"));
            }
            @Override
            public void onReceivedError(WebView v, WebResourceRequest req, WebResourceError err) {
                if (req.isForMainFrame()) toonOffline();
            }
        });

        setContentView(web);
        verbergBalken();
        web.loadUrl(RADIO);
        web.requestFocus();
    }

    private void toonOffline() {
        String html = "<html><body style='margin:0;background:#0b2413;color:#fff;font-family:sans-serif;display:flex;"
            + "align-items:center;justify-content:center;height:100vh;text-align:center'><div>"
            + "<h1 style='color:#f6c64a;font-size:48px;margin:0 0 16px'>Heiligenweg FM</h1>"
            + "<p style='font-size:26px;margin:0 0 24px'>Geen internetverbinding.</p>"
            + "<p style='font-size:22px;color:#b7cbb9'>Druk op OK om het opnieuw te proberen.</p>"
            + "</div><script>document.addEventListener('keydown',function(e){if(e.key==='Enter')location.href='" + RADIO + "';});"
            + "setTimeout(function(){location.href='" + RADIO + "';},15000);</script></body></html>";
        web.loadDataWithBaseURL("https://" + HOST + "/", html, "text/html", "utf-8", null);
    }

    private void klik(String id) {
        web.evaluateJavascript("(function(){var b=document.getElementById('" + id + "');if(b)b.click();})()", null);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_MEDIA_PLAY_PAUSE:
            case KeyEvent.KEYCODE_MEDIA_PLAY:
            case KeyEvent.KEYCODE_MEDIA_PAUSE:
                klik("play"); return true;
            case KeyEvent.KEYCODE_MEDIA_NEXT:
            case KeyEvent.KEYCODE_MEDIA_FAST_FORWARD:
                klik("next"); return true;
            case KeyEvent.KEYCODE_MEDIA_PREVIOUS:
            case KeyEvent.KEYCODE_MEDIA_REWIND:
                klik("prev"); return true;
            case KeyEvent.KEYCODE_BACK:
                // Twee keer kort na elkaar op Terug = app sluiten (zo stopt de radio niet per ongeluk).
                long nu = System.currentTimeMillis();
                if (nu - laatsteTerug < 2500) { finish(); return true; }
                laatsteTerug = nu;
                Toast.makeText(this, "Druk nog een keer op Terug om Heiligenweg FM te sluiten", Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void verbergBalken() {
        View d = getWindow().getDecorView();
        d.setSystemUiVisibility(View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_FULLSCREEN
            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
    }

    @Override public void onWindowFocusChanged(boolean f) { super.onWindowFocusChanged(f); if (f) verbergBalken(); }
    @Override protected void onResume() { super.onResume(); if (web != null) web.onResume(); }
    @Override protected void onPause() { if (web != null) web.onPause(); super.onPause(); }
    @Override protected void onDestroy() { if (web != null) { web.destroy(); web = null; } super.onDestroy(); }
}
