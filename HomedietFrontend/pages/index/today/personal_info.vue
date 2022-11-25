<template>
  <view>
    <uni-section class="input" title="性别" type="line">
      <uni-data-select
        v-model="value"
        :localdata="sex_range"
        @change="sex_change"
      ></uni-data-select>
    </uni-section>
	
	<uni-section title="体重" type="line" padding>
		<input errorMessage v-model="weight" focus placeholder="请输入内容" @input="weight_input"></input>
	</uni-section>


	<uni-section title="身高" type="line" padding>
		<input errorMessage v-model="height" focus placeholder="请输入内容" @input="height_input"></input>
	</uni-section>



	<uni-section title="年龄" type="line" padding>
		<input errorMessage v-model="age" focus placeholder="请输入内容" @input="age_input"></input>
	</uni-section>


	<uni-section title="目标" type="line">
		<uni-data-select
		  v-model="goal_value"
		  :localdata="goal_range"
		  @change="goal_change"
		></uni-data-select>
	</uni-section>
	
	
	<uni-section title="运动系数" type="line">
	  <uni-data-select
	    v-model="tdee_value"
	    :localdata="tdee_range"
	    @change="tdee_change"
	  ></uni-data-select>
	</uni-section>
	
	<button @click="clickButton()">开始计算</button>
	
	
  </view>
</template>

<script>
  export default {
  	data() {
  		return {
			value: '',
			tdee_value: '',
			goal_value:'',
			password: '',
			placeholderStyle: "color:#666;font-size:14px",
			styles: {
				color: '#666',
				borderColor: '#666',
			
			sex: "男",
			weight: 0,
			height: 0,
			age: 0,
			bfr: "减脂",
			tdee: 0,
			exist:0,
			id:0
			},
			
			
			
  			sex_range: [
  				{ value: 0, text: "男"},
  				{ value: 1, text: "女"},
  			],
			goal_range: [  				
				{ value: 0, text: "增肌"},
  				{ value: 1, text: "减脂"},
			],
  			

  			tdee_range: [
  				{ value: 1.2, text: "几乎不运动，常坐办公室"},
  				{ value: 1.375, text: "每天走走路，或者每周轻运动1-3次"},
  				{ value: 1.55, text: "每天在外面跑，或者中等强度运动每周3-5天"},
  				{ value: 1.725, text: "很活跃，体力劳动者，或者每周运动6-7次"},
  				{ value: 1.9, text: "运动员，教练等每天大强度体力劳动者"},
  			],
  			
  			
  		};
  	},
  	
  	
  	onLoad(options) {
		
		if(options){
			this.exist = options.exist
			this.id = options.id
		}
		
		
	},
  	components: {},
  	methods: {
  		async clickButton(){
			const that = this
			const res = await this.$myRequest({
				url: that.exist?'/user/update':'/user/register',
				data: {
					age:that.age,
					frequency:that.tdee,
					gender:that.sex,
				    goal:that.bfr,
					id:that.id,
					height:that.height,
					openid: getApp().globalData.openid,
					username:getApp().globalData.nickName,
					weight:that.weight
				},
				method : that.exist?"PUT":"POST"
			})
			
			Promise.all([res]).then(() => {
				uni.showToast({
					title: '成功',
					icon: 'none'
				})
				uni.navigateTo({
					url : 'today_y?sex=' + this.sex + '&weight=' + this.weight + '&height=' + this.height + '&age=' + this.age + '&bfr=' + this.bfr + '&tdee=' + this.tdee,
				})
			})

  		},
  		
  		sex_change(e){
			this.sex = this.sex_range[e].text;
  		},
		
		tdee_change(e){
			this.tdee = e;
		},
		
		weight_input(e){
			this.weight = e.detail.value;
			
		},
		
		height_input(e){
			this.height = e.detail.value;
		
		},
		
		age_input(e){
			this.age = e.detail.value;
		},
		
		goal_change(e){
			this.bfr = this.goal_range[e].text;
		},
		
		
		
  		
  	},
  };
</script>

<style lang="scss">
  input{
	  height: 10px;
	  margin-left: 15px;
	  font-size: 14px;
  }
  button{
	  margin-top: 10px;
	  height: 35px;
	  width: 160px;
	  font-size: 14px;
	  line-height: 35px;
	  background-color: #0050B3;
	  color: #F9F9F9; 
  }


  
  
</style>