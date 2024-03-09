import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.aviatickets.R
import com.example.aviatickets.databinding.ItemOfferBinding
import com.example.aviatickets.model.entity.Offer

class OfferListAdapter : ListAdapter<Offer, OfferListAdapter.ViewHolder>(DiffCallback()) {

    class ViewHolder(private val binding: ItemOfferBinding) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("StringFormatMatches")
        fun bind(offer: Offer) {
            val context = binding.root.context

            with(binding) {
                departureTime.text = offer.flight.departureTimeInfo
                arrivalTime.text = offer.flight.arrivalTimeInfo
                route.text = context.getString(
                    R.string.route_fmt,
                    offer.flight.departureLocation.code,
                    offer.flight.arrivalLocation.code
                )
                duration.text = context.getString(
                    R.string.time_fmt,
                    offer.flight.duration / 60,
                    offer.flight.duration % 60
                )
                direct.text = context.getString(R.string.direct)
                price.text = context.getString(R.string.price_fmt, offer.price.toString())

//                Glide.with(context)
//                    .load(offer.flight.airline.imageUrl)
//                    .into(airlineImage)
            }
        }

        private fun getTimeFormat(minutes: Int): Pair<Int, Int> = Pair(
            minutes / 60,
            minutes % 60
        )
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemOfferBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class DiffCallback : DiffUtil.ItemCallback<Offer>() {
        override fun areItemsTheSame(oldItem: Offer, newItem: Offer): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Offer, newItem: Offer): Boolean {
            return oldItem == newItem
        }
    }
}

