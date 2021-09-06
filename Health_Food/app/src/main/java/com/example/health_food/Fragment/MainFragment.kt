package com.example.health_food.Fragment

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Rect
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.health_food.FoodDtail
import com.example.health_food.R
import com.example.health_food.databinding.FragmentMainBinding
import com.example.health_food.model.RecommendDTO

class MainFragment : Fragment() {
    private var mainbinding: FragmentMainBinding? = null
    val PiCK_PROFILE_FROM_ALBUM = 10

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mainbinding = FragmentMainBinding.inflate(inflater, container, false)

        mainbinding?.profileImg?.setOnClickListener {
            if(ContextCompat.checkSelfPermission(activity!!,android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                val photoPick = Intent(Intent.ACTION_PICK)
                photoPick.type = "image/*"
                activity!!.startActivityForResult(photoPick, PiCK_PROFILE_FROM_ALBUM)
            }
        }
        mainbinding?.recommendRecyclerview?.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        mainbinding?.recommendRecyclerview?.adapter = RecommendRecyclerViewAdapter()
        mainbinding?.recommendRecyclerview?.animation = AnimationUtils.loadAnimation(context, R.anim.slide_in_to_left)
        PagerSnapHelper().attachToRecyclerView(mainbinding?.recommendRecyclerview)
//        mainbinding?.recommendRecyclerview?.addItemDecoration(HorizontalItemDecorator(80))
        return mainbinding!!.root
    }

    override fun onResume() {
        super.onResume()
        println("연결")
    }

    override fun onStop() {
        super.onStop()
    }

    inner class RecommendRecyclerViewAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {
        val recommendDTO:ArrayList<RecommendDTO>
        init {
            recommendDTO = ArrayList()
            recommendDTO.add(RecommendDTO( imgurl = "https://lh3.googleusercontent.com/proxy/xjwg46WPBQzvpe2Bj25JQUyvVefP2khGnvITudkswllcj9XJRIBE1sYbeFkq7S9rfctA2r8CsuoywPN2MCHxoumezVgnFbthw9CEdACSV5flYuKOuyMB3atN9fPROsrxVsH5v4KKlLR7f1Oq-TrRXSH6MC1LejMu8HLh", foodname = "치킨"))
            recommendDTO.add(RecommendDTO( imgurl = "https://lh3.googleusercontent.com/proxy/xjwg46WPBQzvpe2Bj25JQUyvVefP2khGnvITudkswllcj9XJRIBE1sYbeFkq7S9rfctA2r8CsuoywPN2MCHxoumezVgnFbthw9CEdACSV5flYuKOuyMB3atN9fPROsrxVsH5v4KKlLR7f1Oq-TrRXSH6MC1LejMu8HLh", foodname = "치킨"))
            recommendDTO.add(RecommendDTO( imgurl = "https://lh3.googleusercontent.com/proxy/xjwg46WPBQzvpe2Bj25JQUyvVefP2khGnvITudkswllcj9XJRIBE1sYbeFkq7S9rfctA2r8CsuoywPN2MCHxoumezVgnFbthw9CEdACSV5flYuKOuyMB3atN9fPROsrxVsH5v4KKlLR7f1Oq-TrRXSH6MC1LejMu8HLh", foodname = "치킨"))
            recommendDTO.add(RecommendDTO( imgurl = "https://lh3.googleusercontent.com/proxy/xjwg46WPBQzvpe2Bj25JQUyvVefP2khGnvITudkswllcj9XJRIBE1sYbeFkq7S9rfctA2r8CsuoywPN2MCHxoumezVgnFbthw9CEdACSV5flYuKOuyMB3atN9fPROsrxVsH5v4KKlLR7f1Oq-TrRXSH6MC1LejMu8HLh", foodname = "치킨"))
            recommendDTO.add(RecommendDTO( imgurl = "https://lh3.googleusercontent.com/proxy/xjwg46WPBQzvpe2Bj25JQUyvVefP2khGnvITudkswllcj9XJRIBE1sYbeFkq7S9rfctA2r8CsuoywPN2MCHxoumezVgnFbthw9CEdACSV5flYuKOuyMB3atN9fPROsrxVsH5v4KKlLR7f1Oq-TrRXSH6MC1LejMu8HLh", foodname = "치킨"))
            recommendDTO.add(RecommendDTO( imgurl = "https://lh3.googleusercontent.com/proxy/xjwg46WPBQzvpe2Bj25JQUyvVefP2khGnvITudkswllcj9XJRIBE1sYbeFkq7S9rfctA2r8CsuoywPN2MCHxoumezVgnFbthw9CEdACSV5flYuKOuyMB3atN9fPROsrxVsH5v4KKlLR7f1Oq-TrRXSH6MC1LejMu8HLh", foodname = "치킨"))
            recommendDTO.add(RecommendDTO( imgurl = "https://lh3.googleusercontent.com/proxy/xjwg46WPBQzvpe2Bj25JQUyvVefP2khGnvITudkswllcj9XJRIBE1sYbeFkq7S9rfctA2r8CsuoywPN2MCHxoumezVgnFbthw9CEdACSV5flYuKOuyMB3atN9fPROsrxVsH5v4KKlLR7f1Oq-TrRXSH6MC1LejMu8HLh", foodname = "치킨"))
            recommendDTO.add(RecommendDTO( imgurl = "https://lh3.googleusercontent.com/proxy/xjwg46WPBQzvpe2Bj25JQUyvVefP2khGnvITudkswllcj9XJRIBE1sYbeFkq7S9rfctA2r8CsuoywPN2MCHxoumezVgnFbthw9CEdACSV5flYuKOuyMB3atN9fPROsrxVsH5v4KKlLR7f1Oq-TrRXSH6MC1LejMu8HLh", foodname = "치킨"))
            recommendDTO.add(RecommendDTO( imgurl = "https://lh3.googleusercontent.com/proxy/xjwg46WPBQzvpe2Bj25JQUyvVefP2khGnvITudkswllcj9XJRIBE1sYbeFkq7S9rfctA2r8CsuoywPN2MCHxoumezVgnFbthw9CEdACSV5flYuKOuyMB3atN9fPROsrxVsH5v4KKlLR7f1Oq-TrRXSH6MC1LejMu8HLh", foodname = "치킨"))
            recommendDTO.add(RecommendDTO( imgurl = "https://lh3.googleusercontent.com/proxy/xjwg46WPBQzvpe2Bj25JQUyvVefP2khGnvITudkswllcj9XJRIBE1sYbeFkq7S9rfctA2r8CsuoywPN2MCHxoumezVgnFbthw9CEdACSV5flYuKOuyMB3atN9fPROsrxVsH5v4KKlLR7f1Oq-TrRXSH6MC1LejMu8HLh", foodname = "치킨"))
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recommend, parent, false)
            return CustomViewHolder(view)
        }

        override fun getItemCount(): Int {
            return recommendDTO.size
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            val viewHolder = (holder as CustomViewHolder).itemView
            view?.findViewById<ImageView>(R.id.recommend_img)?.setOnClickListener {
                val intent = Intent(activity, FoodDtail::class.java)
                intent.putExtra("imgurl", recommendDTO[position].imgurl)
                intent.putExtra("foodname", recommendDTO[position].foodname)
                startActivity(intent)
            }
            Glide.with(activity!!)
                .load(recommendDTO[position].imgurl)
                .into(viewHolder.findViewById(R.id.recommend_img))
            viewHolder.findViewById<TextView>(R.id.recommend_txt).text = recommendDTO[position].foodname
        }
    }
    inner class CustomViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)

    inner class HorizontalItemDecorator(private val divHeight: Int):
            RecyclerView.ItemDecoration() {
        override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
            super.getItemOffsets(outRect, view, parent, state)
            outRect.left=divHeight
            outRect.right = divHeight
        }
            }
}