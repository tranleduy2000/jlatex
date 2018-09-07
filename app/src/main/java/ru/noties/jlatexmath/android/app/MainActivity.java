package ru.noties.jlatexmath.android.app;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import ru.noties.debug.AndroidLogDebugOutput;
import ru.noties.debug.Debug;
import ru.noties.jlatexmath.JLatexMathAndroid;
import ru.noties.jlatexmath.JLatexMathDrawable;
import ru.noties.jlatexmath.JLatexMathView;

public class MainActivity extends Activity {

    static {
        Debug.init(new AndroidLogDebugOutput(true));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        final JLatexMathView jLatexMathView = findViewById(R.id.j_latex_math_view);

//        final View view = new View(this);
//        setContentView(view);

//        setContentView(R.layout.activity_main);

//        String latex = "\\begin{array}{cc}";
//        latex += "\\fbox{\\text{A framed box with \\textdbend}}&\\shadowbox{\\text{A shadowed box}}\\cr";
//        latex += "\\doublebox{\\text{A double framed box}}&\\ovalbox{\\text{An oval framed box}}\\cr";
//        latex += "\\end{array}";

//        String latex = "{a \\bangle b} {c \\brace d} {e \\brack f} {g \\choose h}";

//        String latex = "\\text{A long division \\longdiv{12345}{13}";

//        String latex = "\\begin{array}{|c|l|||r|c|}";
//        latex += "\\hline";
//        latex += "\\text{Matrix}&\\multicolumn{2}{|c|}{\\text{Multicolumns}}&\\text{Font sizes commands}\\cr";
//        latex += "\\hline";
//        latex += "\\begin{pmatrix}\\alpha_{11}&\\cdots&\\alpha_{1n}\\cr\\hdotsfor{3}\\cr\\alpha_{n1}&\\cdots&\\alpha_{nn}\\end{pmatrix}&\\Large \\text{Large Right}&\\small \\text{small Left}&\\tiny \\text{tiny Tiny}\\cr";
//        latex += "\\hline";
//        latex += "\\multicolumn{4}{|c|}{\\Huge \\text{Huge Multicolumns}}\\cr";
//        latex += "\\hline";
//        latex += "\\end{array}";

//        String latex = "\\definecolor{gris}{gray}{0.9}";
//        latex += "\\definecolor{noir}{rgb}{0,0,0}";
//        latex += "\\definecolor{bleu}{rgb}{0,0,1}\\newcommand{\\pa}{\\left|}";
//        latex += "\\begin{array}{c}";
//        latex += "\\JLaTeXMath\\\\";
//        latex += "\\begin{split}";
//        latex += " &Тепловой\\ поток\\ \\mathrm{Тепловой\\ поток}\\ \\mathtt{Тепловой\\ поток}\\\\";
//        latex += " &\\boldsymbol{\\mathrm{Тепловой\\ поток}}\\ \\mathsf{Тепловой\\ поток}\\\\";
//        latex += "|I_2| &= \\pa\\int_0^T\\psi(t)\\left\\{ u(a,t)-\\int_{\\gamma(t)}^a \\frac{d\\theta}{k} (\\theta,t) \\int_a^\\theta c(\\xi) u_t (\\xi,t)\\,d\\xi\\right\\}dt\\right|\\\\";
//        latex += "&\\le C_6 \\Bigg|\\pa f \\int_\\Omega \\pa\\widetilde{S}^{-1,0}_{a,-} W_2(\\Omega, \\Gamma_1)\\right|\\ \\right|\\left| |u|\\overset{\\circ}{\\to} W_2^{\\widetilde{A}}(\\Omega;\\Gamma_r,T)\\right|\\Bigg|\\\\";
//        latex += "&\\\\";
//        latex += "&\\textcolor{magenta}{\\mathrm{Produit\\ avec\\ Java\\ et\\ \\LaTeX\\ par\\ }\\mathscr{C}\\mathcal{A}\\mathfrak{L}\\mathbf{I}\\mathtt{X}\\mathbb{T}\\mathsf{E}}\\\\";
//        latex += "&\\begin{pmatrix}\\alpha&\\beta&\\gamma&\\delta\\\\\\aleph&\\beth&\\gimel&\\daleth\\\\\\mathfrak{A}&\\mathfrak{B}&\\mathfrak{C}&\\mathfrak{D}\\\\\\boldsymbol{\\mathfrak{a}}&\\boldsymbol{\\mathfrak{b}}&\\boldsymbol{\\mathfrak{c}}&\\boldsymbol{\\mathfrak{d}}\\end{pmatrix}\\quad{(a+b)}^{\\frac{n}{2}}=\\sqrt{\\sum_{k=0}^n\\tbinom{n}{k}a^kb^{n-k}}\\quad \\Biggl(\\biggl(\\Bigl(\\bigl(()\\bigr)\\Bigr)\\biggr)\\Biggr)\\\\";
//        latex += "&\\forall\\varepsilon\\in\\mathbb{R}_+^*\\ \\exists\\eta>0\\ |x-x_0|\\leq\\eta\\Longrightarrow|f(x)-f(x_0)|\\leq\\varepsilon\\\\";
//        latex += "&\\det\\begin{bmatrix}a_{11}&a_{12}&\\cdots&a_{1n}\\\\a_{21}&\\ddots&&\\vdots\\\\\\vdots&&\\ddots&\\vdots\\\\a_{n1}&\\cdots&\\cdots&a_{nn}\\end{bmatrix}\\overset{\\mathrm{def}}{=}\\sum_{\\sigma\\in\\mathfrak{S}_n}\\varepsilon(\\sigma)\\prod_{k=1}^n a_{k\\sigma(k)}\\\\";
//        latex += "&\\Delta f(x,y)=\\frac{\\partial^2f}{\\partial x^2}+\\frac{\\partial^2f}{\\partial y^2}\\qquad\\qquad \\fcolorbox{noir}{gris}{n!\\underset{n\\rightarrow+\\infty}{\\sim} {\\left(\\frac{n}{e}\\right)}^n\\sqrt{2\\pi n}}\\\\";
//        latex += "&\\sideset{_\\alpha^\\beta}{_\\gamma^\\delta}{\\begin{pmatrix}a&b\\\\c&d\\end{pmatrix}}\\xrightarrow[T]{n\\pm i-j}\\sideset{^t}{}A\\xleftarrow{\\overrightarrow{u}\\wedge\\overrightarrow{v}}\\underleftrightarrow{\\iint_{\\mathds{R}^2}e^{-\\left(x^2+y^2\\right)}\\,\\mathrm{d}x\\mathrm{d}y}";
//        latex += "\\end{split}\\\\";
//        latex += "\\rotatebox{30}{\\sum_{n=1}^{+\\infty}}\\quad\\mbox{Mirror rorriM}\\reflectbox{\\mbox{Mirror rorriM}}";
//        latex += "\\end{array}";

//
        String latex = "\\begin{array}{l}";
        latex += "\\forall\\varepsilon\\in\\mathbb{R}_+^*\\ \\exists\\eta>0\\ |x-x_0|\\leq\\eta\\Longrightarrow|f(x)-f(x_0)|\\leq\\varepsilon\\\\";
        latex += "\\det\\begin{bmatrix}a_{11}&a_{12}&\\cdots&a_{1n}\\\\a_{21}&\\ddots&&\\vdots\\\\\\vdots&&\\ddots&\\vdots\\\\a_{n1}&\\cdots&\\cdots&a_{nn}\\end{bmatrix}\\overset{\\mathrm{def}}{=}\\sum_{\\sigma\\in\\mathfrak{S}_n}\\varepsilon(\\sigma)\\prod_{k=1}^n a_{k\\sigma(k)}\\\\";
        latex += "\\sideset{_\\alpha^\\beta}{_\\gamma^\\delta}{\\begin{pmatrix}a&b\\\\c&d\\end{pmatrix}}\\\\";
        latex += "\\int_0^\\infty{x^{2n} e^{-a x^2}\\,dx} = \\frac{2n-1}{2a} \\int_0^\\infty{x^{2(n-1)} e^{-a x^2}\\,dx} = \\frac{(2n-1)!!}{2^{n+1}} \\sqrt{\\frac{\\pi}{a^{2n+1}}}\\\\";
        latex += "\\int_a^b{f(x)\\,dx} = (b - a) \\sum\\limits_{n = 1}^\\infty  {\\sum\\limits_{m = 1}^{2^n  - 1} {\\left( { - 1} \\right)^{m + 1} } } 2^{ - n} f(a + m\\left( {b - a} \\right)2^{-n} )\\\\";
        latex += "\\int_{-\\pi}^{\\pi} \\sin(\\alpha x) \\sin^n(\\beta x) dx = \\textstyle{\\left \\{ \\begin{array}{cc} (-1)^{(n+1)/2} (-1)^m \\frac{2 \\pi}{2^n} \\binom{n}{m} & n \\mbox{ odd},\\ \\alpha = \\beta (2m-n) \\\\ 0 & \\mbox{otherwise} \\\\ \\end{array} \\right .}\\\\";
        latex += "L = \\int_a^b \\sqrt{ \\left|\\sum_{i,j=1}^ng_{ij}(\\gamma(t))\\left(\\frac{d}{dt}x^i\\circ\\gamma(t)\\right)\\left(\\frac{d}{dt}x^j\\circ\\gamma(t)\\right)\\right|}\\,dt\\\\";
        latex += "\\begin{array}{rl} s &= \\int_a^b\\left\\|\\frac{d}{dt}\\vec{r}\\,(u(t),v(t))\\right\\|\\,dt \\\\ &= \\int_a^b \\sqrt{u'(t)^2\\,\\vec{r}_u\\cdot\\vec{r}_u + 2u'(t)v'(t)\\, \\vec{r}_u\\cdot\\vec{r}_v+ v'(t)^2\\,\\vec{r}_v\\cdot\\vec{r}_v}\\,\\,\\, dt. \\end{array}\\\\";
        latex += "\\end{array}";

//        String latex = "\\begin{array}{lr}\\mbox{\\textcolor{Blue}{Russian}}&\\mbox{\\textcolor{Melon}{Greek}}\\\\";
//        latex += "\\mbox{" + "привет мир".toUpperCase() + "}&\\mbox{" + "γειά κόσμο".toUpperCase()
//                + "}\\\\";
//        latex += "\\mbox{привет мир}&\\mbox{γειά κόσμο}\\\\";
//        latex += "\\mathbf{\\mbox{привет мир}}&\\mathbf{\\mbox{γειά κόσμο}}\\\\";
//        latex += "\\mathit{\\mbox{привет мир}}&\\mathit{\\mbox{γειά κόσμο}}\\\\";
//        latex += "\\mathsf{\\mbox{привет мир}}&\\mathsf{\\mbox{γειά κόσμο}}\\\\";
//        latex += "\\mathtt{\\mbox{привет мир}}&\\mathtt{\\mbox{γειά κόσμο}}\\\\";
//        latex += "\\mathbf{\\mathit{\\mbox{привет мир}}}&\\mathbf{\\mathit{\\mbox{γειά κόσμο}}}\\\\";
//        latex += "\\mathbf{\\mathsf{\\mbox{привет мир}}}&\\mathbf{\\mathsf{\\mbox{γειά κόσμο}}}\\\\";
//        latex += "\\mathsf{\\mathit{\\mbox{привет мир}}}&\\mathsf{\\mathit{\\mbox{γειά κόσμο}}}\\\\";
//        latex += "&\\\\";
//        latex += "\\mbox{\\textcolor{Salmon}{Bulgarian}}&\\mbox{\\textcolor{Tan}{Serbian}}\\\\";
//        latex += "\\mbox{здравей свят}&\\mbox{Хелло уорлд}\\\\";
//        latex += "&\\\\";
//        latex += "\\mbox{\\textcolor{Turquoise}{Bielorussian}}&\\mbox{\\textcolor{LimeGreen}{Ukrainian}}\\\\";
//        latex += "\\mbox{прывітаньне Свет}&\\mbox{привіт світ}\\\\";
//        latex += "\\end{array}";

//        final JLatexMathDrawable drawable = JLatexMathDrawable.builder(latex)
//                .textSize(70)
//                .padding(8)
//                .background(0xFFff0000)
//                .align(JLatexMathDrawable.ALIGN_RIGHT)
//                .build();
//
//        view.setBackground(drawable);


//        final LatexMathView latexMathView = findViewById(R.id.latex_math_view);
//        latexMathView.setLatexMath(latex);

        jLatexMathView.setLatex(latex);
    }
}
