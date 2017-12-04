package com.fable.kscc.api.beans.component;

import java.util.List;

import com.google.common.collect.Lists;

/**
 * 创会参数
 */
public class ConfParam {
	private String name = "";
	private int duration = 60;
	private int conf_level = 1;
	private int bitrate = 4096;
	private int closed_conf = 0;
	private int safe_conf = 0;
	private String password = "";
	private int encrypted_type = 0;
	private int conf_type = 0;
	private int call_mode = 0;
	private int call_times = 20;
	private int call_interval = 5;
	private int mute = 0;
	private int silence = 0;
	private int video_quality = 0;
	private String encrypted_key = "";
	private int dual_mode = 1;
	private int voice_activity_detection = 0;
	private int vacinterval = 5;
	private int cascade_mode = 0;
	private int cascade_upload = 0;
	private int cascade_return = 0;
	private int cascade_return_para = 0;
	private int public_conf = 1;
	private int max_join_mt = 8;
	private int auto_end = 0;
	private int preoccpuy_resource = 0;
	private Speaker speaker;
	private Chairman chairman;
	private Mix mix;
	// private Satellite satellite;
	// private Record record;
	// private Multicast multicast;
	private List<VideoFormats> video_formats;
	/*需要注释*/
//	 private List<Integer> audio_formats;
	// private List<DualFormat> dual_formats;
	private List<InviteMember> invite_members;
	private Vmp vmp;
	private List<Vip> vips;
	private Poll poll;

	public int getConf_level() {
		return conf_level;
	}

	public void setConf_level(int conf_level) {
		this.conf_level = conf_level;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public int getBitrate() {
		return bitrate;
	}

	public void setBitrate(int bitrate) {
		this.bitrate = bitrate;
	}

	public int getClosed_conf() {
		return closed_conf;
	}

	/*
	 * public List<DualFormat> getDual_formats() { return dual_formats; }
	 *
	 * public void setDual_formats(List<DualFormat> dual_formats) {
	 * this.dual_formats = dual_formats; }
	 */

	public void setClosed_conf(int closed_conf) {
		this.closed_conf = closed_conf;
	}

	public int getSafe_conf() {
		return safe_conf;
	}

	public void setSafe_conf(int safe_conf) {
		this.safe_conf = safe_conf;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getEncrypted_type() {
		return encrypted_type;
	}

	public void setEncrypted_type(int encrypted_type) {
		this.encrypted_type = encrypted_type;
	}

	public String getEncrypted_key() {
		return encrypted_key;
	}

	public void setEncrypted_key(String encrypted_key) {
		this.encrypted_key = encrypted_key;
	}

	public int getConf_type() {
		return conf_type;
	}

	public void setConf_type(int conf_type) {
		this.conf_type = conf_type;
	}

	public int getCall_times() {
		return call_times;
	}

	public void setCall_times(int call_times) {
		this.call_times = call_times;
	}

	public int getCall_interval() {
		return call_interval;
	}

	public void setCall_interval(int call_interval) {
		this.call_interval = call_interval;
	}

	public int getMute() {
		return mute;
	}

	public void setMute(int mute) {
		this.mute = mute;
	}

	public int getVideo_quality() {
		return video_quality;
	}

	public void setVideo_quality(int video_quality) {
		this.video_quality = video_quality;
	}

	public int getDual_mode() {
		return dual_mode;
	}

	public void setDual_mode(int dual_mode) {
		this.dual_mode = dual_mode;
	}

	public int getVoice_activity_detection() {
		return voice_activity_detection;
	}

	public void setVoice_activity_detection(int voice_activity_detection) {
		this.voice_activity_detection = voice_activity_detection;
	}

	public int getCascade_mode() {
		return cascade_mode;
	}

	public void setCascade_mode(int cascade_mode) {
		this.cascade_mode = cascade_mode;
	}

	public int getCascade_upload() {
		return cascade_upload;
	}

	public void setCascade_upload(int cascade_upload) {
		this.cascade_upload = cascade_upload;
	}

	public int getCascade_return() {
		return cascade_return;
	}

	public void setCascade_return(int cascade_return) {
		this.cascade_return = cascade_return;
	}

	public int getCascade_return_para() {
		return cascade_return_para;
	}

	public void setCascade_return_para(int cascade_return_para) {
		this.cascade_return_para = cascade_return_para;
	}

	public int getPublic_conf() {
		return public_conf;
	}

	public void setPublic_conf(int public_conf) {
		this.public_conf = public_conf;
	}

	public int getMax_join_mt() {
		return max_join_mt;
	}

	public void setMax_join_mt(int max_join_mt) {
		this.max_join_mt = max_join_mt;
	}

	public int getAuto_end() {
		return auto_end;
	}

	public void setAuto_end(int auto_end) {
		this.auto_end = auto_end;
	}

	public Speaker getSpeaker() {
		return speaker;
	}

	public void setSpeaker(Speaker speaker) {
		this.speaker = speaker;
	}

	public Chairman getChairman() {
		return chairman;
	}

	public void setChairman(Chairman chairman) {
		this.chairman = chairman;
	}

	public Mix getMix() {
		return mix;
	}

	public void setMix(Mix mix) {
		this.mix = mix;
	}

	/*
	 * public Satellite getSatellite() { return satellite; }
	 * 
	 * public void setSatellite(Satellite satellite) { this.satellite =
	 * satellite; }
	 * 
	 * public Record getRecord() { return record; }
	 * 
	 * public void setRecord(Record record) { this.record = record; }
	 * 
	 * public Multicast getMulticast() { return multicast; }
	 * 
	 * public void setMulticast(Multicast multicast) { this.multicast =
	 * multicast; }
	 */

	public List<VideoFormats> getVideo_formats() {
		return video_formats;
	}

	public void setVideo_formats(List<VideoFormats> video_formats) {
		this.video_formats = video_formats;
	}

	/**
	 * get set 需要注释
	 * @return
	 */
//	  public List<Integer> getAudio_formats() { return audio_formats; }
//
//	 public void setAudio_formats(List<Integer> audio_formats) {
//	  this.audio_formats = audio_formats; }


	public List<InviteMember> getInvite_members() {
		return invite_members;
	}

	public void setInvite_members(List<InviteMember> invite_members) {
		this.invite_members = invite_members;
	}

	public Vmp getVmp() {
		return vmp;
	}

	public void setVmp(Vmp vmp) {
		this.vmp = vmp;
	}

	public List<Vip> getVips() {
		return vips;
	}

	public void setVips(List<Vip> vips) {
		this.vips = vips;
	}

	public Poll getPoll() {
		return poll;
	}

	public void setPoll(Poll poll) {
		this.poll = poll;
	}

	public int getCall_mode() {
		return call_mode;
	}

	public void setCall_mode(int call_mode) {
		this.call_mode = call_mode;
	}

	public int getVacinterval() {
		return vacinterval;
	}

	public void setVacinterval(int vacinterval) {
		this.vacinterval = vacinterval;
	}

	public void addInviteMembers(List<String> mtEList) {
		if (this.invite_members == null) {
			this.invite_members = Lists.newArrayList();
		}
		for (String mte : mtEList) {
			InviteMember mem = new InviteMember();
			mem.setAccount_type(5);// e164号码
			mem.setAccount(mte);
			this.invite_members.add(mem);
		}
	}

	public int getSilence() {
		return silence;
	}

	public void setSilence(int silence) {
		this.silence = silence;
	}

	public int getPreoccpuy_resource() {
		return preoccpuy_resource;
	}

	public void setPreoccpuy_resource(int preoccpuy_resource) {
		this.preoccpuy_resource = preoccpuy_resource;
	}
}
