package com.flux.bean;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ViewHolder {

	/**
	 * 存储item中所用控件引用的容器
	 * 
	 * key 资源ID
	 * value 控件的引用
	 */
	private SparseArray<View> views;
	private View converView;
	private int positon;
	
	/**
	 * 私有化的构造器，由类内部来管理该实例
	 * @param context  上下文
	 * @param itemLayoutResId  item布局文件的资源ID
 	 * @param position         BaseAdpater.getView的传入参数
	 * @param parent           BaseAdpater.getView的传入参数
	 */
	private ViewHolder(Context context,int itemLayoutResId,int position,ViewGroup parent){
		this.views = new SparseArray<View>();
		this.positon = position;
		this.converView = LayoutInflater.from(context).inflate(itemLayoutResId, parent, false);
		converView.setTag(this);
	}
	
	/**
	 * 得到一个ViewHolder对象
	 * 
	 * @param context              上下文对象
	 * @param itemLayoutResId      item布局文件的资源Id
	 * @param position             BaseAdpater.getView的传入参数          
	 * @param converView           BaseAdpater.getView的传入参数
	 * @param parent               BaseAdpater.getView的传入参数
	 * @return                     一个ViewHolder对象
	 */
	public static ViewHolder getViewHolder(Context context,int itemLayoutResId,int position,View converView,ViewGroup parent){
		
		if(converView==null){
			return new ViewHolder(context, itemLayoutResId, position, parent);
		}else{
			ViewHolder viewHolder = (ViewHolder) converView.getTag();
			viewHolder.positon = position;
			return viewHolder;
		}
	}
	
	public View getConverView(){
		return this.converView;
	}
	
	public int getPositon() {
		return positon;
	}

	/**
	 * [核心部分]
	 * 根据控件的资源id，获取控件
	 * @param viewResId   控件的资源Id
	 * @return            控件的引用
	 */
	@SuppressWarnings("unchecked")
	public <T extends View> T getView(int viewResId){
		View view = views.get(viewResId);
		if(view==null){
			view = converView.findViewById(viewResId);
			views.put(viewResId, view);
		}
		
		return (T)view;
	}
	
	public ViewHolder setText(int viewResId,String text){
		TextView view = getView(viewResId);
		view.setText(text);
		return this;
	}
}
