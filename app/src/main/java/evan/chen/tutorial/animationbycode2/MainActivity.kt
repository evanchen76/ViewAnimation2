package evan.chen.tutorial.animationbycode2

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.*
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import android.view.animation.Animation
import android.view.animation.Animation.AnimationListener


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        animationSet.setOnClickListener {

            //新增一個動畫組合
            val animSet = AnimationSet(true)
            animSet.isFillEnabled = true

            //透明過至0.2
            val alphaAnimation = AlphaAnimation(1.0f, 0.2f)
            alphaAnimation.duration = 1000

            //x座標增加100，y座標減少100
            val translateAnimation = TranslateAnimation(
                    0f,
                    100f,
                    0f,
                    -100f)
            translateAnimation.duration = 1000

            //將scaleAnimation加入AnimationSet動畫組合
            animSet.addAnimation(alphaAnimation)
            animSet.addAnimation(translateAnimation)

            this.image.startAnimation(animSet)
        }

        reverse.setOnClickListener {

            val animation = AlphaAnimation(1.0f, 0.2f)
            animation.duration = 1000
            //無限次重覆
            animation.repeatCount = Animation.INFINITE
            //重覆模式為反轉
            animation.repeatMode = Animation.RESTART

            animation.setAnimationListener(object : AnimationListener {

                override fun onAnimationStart(animation: Animation) {
                    println("動畫開始")
                }

                override fun onAnimationEnd(animation: Animation) {
                    println("動畫結束")
                }

                override fun onAnimationRepeat(animation: Animation) {
                    println("動畫重新開始")
                }
            })

            animation.cancel()

            this.image.startAnimation(animation)
        }

        translate.setOnClickListener {
            //x座標增加100，y座標減少100
            val animation = TranslateAnimation(
                    0f,
                    800f,
                    0f,
                    0f)
            animation.duration = 1000
            animation.fillAfter = true
//            animation.interpolator = AccelerateInterpolator() //加速
//            animation.interpolator = DecelerateInterpolator() //減速
//            animation.interpolator = LinearInterpolator() //均速
//            animation.interpolator = OvershootInterpolator() //快速到終點，超過再回到結束位置
//            animation.interpolator = AccelerateDecelerateInterpolator() //先加速再減速
//            animation.interpolator = AnticipateInterpolator() //先後退再加速前進
//            animation.interpolator = AnticipateOvershootInterpolator() //先後退再加速前進、超過終點後再回到結束樣式
//            animation.interpolator = BounceInterpolator() //快速到最後彈回
            animation.interpolator = CycleInterpolator(3.0f) //來回彈動
            this.image.startAnimation(animation)
        }
    }
}
