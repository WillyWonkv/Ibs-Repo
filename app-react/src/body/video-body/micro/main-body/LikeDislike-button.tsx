import React from "react";
import "./LikeDislike-button.css"

interface Props{
    numeroLike : number;
}

const LikeDislikeButton = ({numeroLike} : Props) => {

    return(

        <div className="likedislike flex">
            <a href="#" className="buttonlike flex" id="buttonlike">
                <i className="fa-solid fa-thumbs-up colorlike"></i>
                <span className="numerolike fontYT" id="numerolike">{numeroLike}</span>
            </a>
            <a href="#" className="buttondislike flex" id="buttondislike">
                <i className="fa-solid fa-thumbs-down colorlike"></i>
            </a>
        </div>

    );

}

export default LikeDislikeButton;