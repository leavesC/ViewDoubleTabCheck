package github.leavesc.library.double_click

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.ButterKnife
import butterknife.OnClick
import com.chad.library.adapter.base.BaseQuickAdapter
import github.leavesc.asm.double_click.CheckViewOnClick
import github.leavesc.asm.double_click.UncheckViewOnClick
import github.leavesc.library.R
import github.leavesc.library.R2

/**
 * @Author: leavesC
 * @Date: 2021/12/7 21:17
 * @Desc:
 */
class ViewDoubleClickCheckLibraryActivity : AppCompatActivity() {

    private var clickIndex = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_double_click_check_library)
        findViewById<TextView>(R.id.tvObjectUnCheck).setOnClickListener(object :
            View.OnClickListener {

            @UncheckViewOnClick
            override fun onClick(v: View) {
                onClickView()
            }
        })
        findViewById<TextView>(R.id.tvObjectCheck).setOnClickListener(object :
            View.OnClickListener {
            override fun onClick(v: View) {
                onClickView()
            }
        })
        findViewById<TextView>(R.id.tvLambda).setOnClickListener {
            onClickView()
        }
        ButterKnife.bind(this)

        val demoAdapter = DemoAdapter.get()
        val rvStringList = findViewById<RecyclerView>(R.id.rvStringList)
        rvStringList.adapter = demoAdapter
        rvStringList.layoutManager = LinearLayoutManager(this)
        demoAdapter.onItemClickListener =
            BaseQuickAdapter.OnItemClickListener { adapter, view, position ->
                onClickView()
            }
        demoAdapter.onItemChildClickListener = object : BaseQuickAdapter.OnItemChildClickListener {
            override fun onItemChildClick(
                adapter: BaseQuickAdapter<*, *>?,
                view: View,
                position: Int
            ) {
                if (view.id == R.id.viewChild) {
                    onClickChildView()
                }
            }
        }
    }

    @OnClick(R2.id.tvButterKnife)
    fun onClickViewByButterKnife(view: View) {
        val viewId = view.id
        if (viewId == R.id.tvButterKnife) {
            onClickView()
        }
    }

    @CheckViewOnClick
    fun onClickByXml(view: View) {
        onClickView()
    }

    private fun onClickView() {
        findViewById<TextView>(R.id.tvIndex).text = (clickIndex++).toString()
    }

    private fun onClickChildView() {
        findViewById<TextView>(R.id.tvIndex).text = "onClickChildView" + (clickIndex++).toString()
    }

}