package uk.co.markormesher.resttimer

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.list_item_timer.view.*

class TimerRecyclerAdapter(val context: Context, val listener: TimerRecyclerClickListener? = null): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

	private val layoutInflater by lazy { LayoutInflater.from(context)!! }
	val timers = ArrayList<Int>()

	override fun getItemCount(): Int = timers.size

	override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
		return TimerViewHolder(layoutInflater.inflate(R.layout.list_item_timer, parent, false))
	}

	override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
		with(holder as TimerViewHolder) {
			val timer = timers[position]
			duration.text = timer.toString()
			units.text = context.getString(if (timer > 1) R.string.sec_plural else R.string.sec_singular)
			rootView.setOnClickListener { listener?.onTimerClick(timer) }
			rootView.setOnLongClickListener { listener?.onTimerLongClick(timer) ?: false }
		}
	}

	class TimerViewHolder(v: View): RecyclerView.ViewHolder(v) {
		val rootView = v
		val duration = v.duration!!
		val units = v.units!!
	}

	interface TimerRecyclerClickListener {
		fun onTimerClick(duration: Int)
		fun onTimerLongClick(duration: Int): Boolean
	}

}
