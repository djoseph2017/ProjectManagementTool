import {GET_ERRORS} from "../actions/typeActions";

const initialState = {}

export default function(state = initialState, action){
    switch(action.type){
        case GET_ERRORS:
            return action.payload;
        default:
            return this.state;
    }
}