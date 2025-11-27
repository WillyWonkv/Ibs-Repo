import "./Body.css"
import { Button, Dropdown, Flex } from "antd"

const bodyStyle:React.CSSProperties = {
    margin:"0",
    padding:"0",
    marginTop:"64px",
    height: "calc(100vh - 64px)",
    backgroundColor:"#464646" 
}

const contentStyle:React.CSSProperties = {
    margin:"20px 50px 0 50px"
}

export const Body = () =>{

    return (
        <Flex style={bodyStyle} className="body">
            <Flex style={contentStyle}>
                    <Dropdown 
                        menu={{}} 
                        placement="bottom" 
                        trigger={["click"]}
                        popupRender={(menu) => (
                            <div className="contentStyle">
                                <div className="menuStyle">
                                    {menu}
                                </div>
                            </div>
                        )}
                        >
                        <Button
                            shape="round"
                            type="text"
                            className="genresbutton"
                            style={{color:"white"}}
                            >Genres</Button>
                    </Dropdown>
            </Flex>
        </Flex>
    )

}