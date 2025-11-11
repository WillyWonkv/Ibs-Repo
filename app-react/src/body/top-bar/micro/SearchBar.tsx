import React, { useState } from 'react';
import "./SearchBar.css";

const SearchBar : React.FC= () => {

    const [focused, setFocused] = useState(false)

    return(
        <div className={`search_bar flex ${focused ? "focused" : ""}`} id="searchbardiv">
          {focused && <i className="fa-solid fa-magnifying-glass lente-extra"></i>}
          <input 
            className={`search_input ${focused ? "focused" : ""}`} 
            type="text" 
            placeholder="Search.." 
            id="searchbar"
            onFocus={() => setFocused(true)}
            onBlur={() => setFocused(false)}/>
          <a className="button flex" href="#" id="searchbutton">
            <i className="fa-solid fa-magnifying-glass lente" id="lente"></i>
          </a>
        </div>
    )

}

export default SearchBar;