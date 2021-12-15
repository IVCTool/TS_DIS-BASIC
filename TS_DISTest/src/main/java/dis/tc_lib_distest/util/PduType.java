package dis.tc_lib_distest.util;

public enum PduType {
	ENTITY_STATE {
		public String getDescription() {
			return "Entity State";
		}
	},
	FIRE {
		public String getDescription() {
			return "Fire";
		}
	},
	DETONATION {
		public String getDescription() {
			return "Detonation";
		}
	},
	COLLISION {
		public String getDescription() {
			return "Collision";
		}
	},
	SERVICE_REQUEST {
		public String getDescription() {
			return "Service Request";
		}
	},
	RESUPPLY_OFFER {
		public String getDescription() {
			return "Resupply Offer";
		}
	},
	RESUPPLY_RECEIVED {
		public String getDescription() {
			return "Resupply Received";
		}
	},
	RESUPPLY_CANCEL {
		public String getDescription() {
			return "Resupply Cancel";
		}
	},
	REPAIR_COMPLETE {
		public String getDescription() {
			return "Repair Complete";
		}
	},
	REPAIR_RESPONSE {
		public String getDescription() {
			return "Repair Response";
		}
	},
	CREATE_ENTITY {
		public String getDescription() {
			return "Create Entity";
		}
	},
	REMOVE_ENTITY {
		public String getDescription() {
			return "Remove Entity";
		}
	},
	START_RESUME {
		public String getDescription() {
			return "Start / Resume";
		}
	},
	STOP_FREEZE {
		public String getDescription() {
			return "Stop / Freeze";
		}
	},
	ACKNOWLEDGE {
		public String getDescription() {
			return "Acknowledge";
		}
	},
	ACTION_REQUEST {
		public String getDescription() {
			return "Action Request";
		}
	},
	ACTION_RESPONSE {
		public String getDescription() {
			return "Action Response";
		}
	},
	DATA_QUERY {
		public String getDescription() {
			return "Data Query";
		}
	},
	SET_DATA {
		public String getDescription() {
			return "Set Data";
		}
	},
	DATA {
		public String getDescription() {
			return "Data";
		}
	},
	EVENT_REPORT {
		public String getDescription() {
			return "Event Report";
		}
	},
	COMMENT {
		public String getDescription() {
			return "Comment";
		}
	},
	ELECTROMAGNETIC_EMISSION {
		public String getDescription() {
			return "Electromagnetc Emission";
		}
	},
	DESIGNATOR {
		public String getDescription() {
			return "Designator";
		}
	},
	TRANSMITTER {
		public String getDescription() {
			return "Transmitter";
		}
	},
	SIGNAL {
		public String getDescription() {
			return "Signal";
		}
	},
	RECEIVER {
		public String getDescription() {
			return "Receiver";
		}
	},
	UNDERWATER_ACOUSTIC {
		public String getDescription() {
			return "Underwater Acoustic";
		}
	},
	SUPPLEMENTAL_EMISSION_ENTITY_STATE {
		public String getDescription() {
			return "Supplemental Emission Entity State";
		}
	},
	INTERCOM_SIGNAL {
		public String getDescription() {
			return "Intercom Signal";
		}
	},
	INTERCOM_CONTROL {
		public String getDescription() {
			return "Intercom Control";
		}
	},
	AGGREGATE_STATE {
		public String getDescription() {
			return "Aggregate State";
		}
	},
	ISGROUPOF {
		public String getDescription() {
			return "IsGroupOf";
		}
	},
	TRANSFER_CONTROL {
		public String getDescription() {
			return "Transfer Control";
		}
	},
	ISPARTOF {
		public String getDescription() {
			return "IsPartOf";
		}
	},
	MINEFIELD_STATE {
		public String getDescription() {
			return "Minefield State";
		}
	},
	MINEFIELD_QUERY {
		public String getDescription() {
			return "Minefield Query";
		}
	},
	MINEFIELD_DATA {
		public String getDescription() {
			return "Minefield Data";
		}
	},
	MINEFIELD_RESPONSE_NAK {
		public String getDescription() {
			return "Minefield Response NAK";
		}
	},
	ENVIRONMENTAL_PROCESS {
		public String getDescription() {
			return "Environmental Process";
		}
	},
	GRIDDED_DATA {
		public String getDescription() {
			return "Group Data";
		}
	},
	POINT_OBJECT_STATE {
		public String getDescription() {
			return "Point Object State";
		}
	},
	LINEAR_OBJECT_STATE {
		public String getDescription() {
			return "Linear Object State";
		}
	},
	AREAL_OBJECT_STATE {
		public String getDescription() {
			return "Aerial Object State";
		}
	},
	CREATE_ENTITY_R {
		public String getDescription() {
			return "Create Entity Reliable";
		}
	},
	REMOVE_ENTITY_R {
		public String getDescription() {
			return "Remove Entity Reliable";
		}
	},
	START_RESUME_R {
		public String getDescription() {
			return "Start / Resme Reliable";
		}
	},
	STOP_FREEZE_R {
		public String getDescription() {
			return "Stop / Freeze Reliable";
		}
	},
	ACKNOWLEDGE_R {
		public String getDescription() {
			return "Acknowledge Reliable";
		}
	},
	ACTION_REQUEST_R {
		public String getDescription() {
			return "Action Request Reliable";
		}
	},
	ACTION_RESPONSE_R {
		public String getDescription() {
			return "Action Response Reliable";
		}
	},
	DATA_QUERY_R {
		public String getDescription() {
			return "Data Query Reliable";
		}
	},
	SET_DATA_R {
		public String getDescription() {
			return "Set Data Reliable";
		}
	},
	DATA_R {
		public String getDescription() {
			return "Data Reliable";
		}
	},
	EVENT_REPORT_R {
		public String getDescription() {
			return "Event Report Reliable";
		}
	},
	COMMENT_R {
		public String getDescription() {
			return "Comment Reliable";
		}
	},
	SET_RECORD_R {
		public String getDescription() {
			return "Set Record Reliable";
		}
	},
	RECORD_QUERY_R {
		public String getDescription() {
			return "Record Query Reliable";
		}
	},
	COLLISION_ELASTIC {
		public String getDescription() {
			return "Collision Elastic";
		}
	},
	ENTITY_STATE_UPDATE {
		public String getDescription() {
			return "Entity State Update";
		}
	};
	
	public static PduType lookup(int type) {
		
		switch(type) {
			case 1: return ENTITY_STATE;
			case 2: return FIRE;
			case 3: return DETONATION;
			case 4: return COLLISION;
			case 5: return SERVICE_REQUEST;
			case 6: return	RESUPPLY_OFFER;
			case 7: return	RESUPPLY_RECEIVED;
			case 8: return	RESUPPLY_CANCEL;
			case 9: return	REPAIR_COMPLETE;
			case 10: return	REPAIR_RESPONSE;
			case 11: return	CREATE_ENTITY;
			case 12: return	REMOVE_ENTITY;
			case 13: return	START_RESUME;
			case 14: return	STOP_FREEZE;
			case 15: return	ACKNOWLEDGE;
			case 16: return	ACTION_REQUEST;
			case 17: return	ACTION_RESPONSE;
			case 18: return	DATA_QUERY;
			case 19: return	SET_DATA;
			case 20: return	DATA;
			case 21: return	EVENT_REPORT;
			case 22: return	COMMENT;
			case 23: return	ELECTROMAGNETIC_EMISSION;
			case 24: return	DESIGNATOR;
			case 25: return	TRANSMITTER;
			case 26: return	SIGNAL;
			case 27: return	RECEIVER;
			case 28: return	UNDERWATER_ACOUSTIC;
			case 29: return	SUPPLEMENTAL_EMISSION_ENTITY_STATE;
			case 30: return	INTERCOM_SIGNAL;
			case 31: return	INTERCOM_CONTROL;
			case 32: return	AGGREGATE_STATE;
			case 33: return	ISGROUPOF;
			case 34: return	TRANSFER_CONTROL;
			case 35: return	ISPARTOF;
			case 36: return	MINEFIELD_STATE;
			case 37: return	MINEFIELD_QUERY;
			case 38: return	MINEFIELD_DATA;
			case 39: return	MINEFIELD_RESPONSE_NAK;
			case 40: return	ENVIRONMENTAL_PROCESS;
			case 41: return	GRIDDED_DATA;
			case 42: return	POINT_OBJECT_STATE;
			case 43: return	LINEAR_OBJECT_STATE;
			case 44: return	AREAL_OBJECT_STATE;
			case 45: return	CREATE_ENTITY_R;
			case 46: return	REMOVE_ENTITY_R;
			case 47: return	START_RESUME_R;
			case 48: return	STOP_FREEZE_R;
			case 49: return	ACKNOWLEDGE_R;
			case 50: return	ACTION_REQUEST_R;
			case 51: return	ACTION_RESPONSE_R;
			case 52: return	DATA_QUERY_R;
			case 53: return	SET_DATA_R;
			case 54: return	DATA_R;
			case 56: return	EVENT_REPORT_R;
			case 57: return	COMMENT_R;
			case 58: return	SET_RECORD_R;
			case 59: return	RECORD_QUERY_R;
			case 60: return	COLLISION_ELASTIC;
			case 61: return	ENTITY_STATE_UPDATE;
			default: return null;
		}
	}
	
	public String getDescription() {
		return PduType.valueOf(this.name()).getDescription();
	}
	
}