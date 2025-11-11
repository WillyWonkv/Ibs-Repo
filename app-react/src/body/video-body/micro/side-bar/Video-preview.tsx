import React, { useState } from "react";
import "./Video-preview.css";

interface Props{
    src : string;
}

const VideoPreview = ({src} : Props) => {

    const[Enter, setEnter] = useState(false);

    return(

        <div className={`videopreview ${Enter ? "enter" : ""}`}  
            onMouseEnter={() => setEnter(true)}
            onMouseLeave={() => setEnter(false)}>

            {Enter && <a className="likedislikecontainer l-c-p">
                        <i className="fa-solid fa-thumbs-up buttonlikevideo"></i>
                    </a>}    
            {Enter && <a className="likedislikecontainer d-c-p">
                        <i className="fa-solid fa-thumbs-down buttonlikevideo"></i>
                    </a>} 

            <img src={src} alt="Video preview"/>
        </div>

    );

}

export default VideoPreview;