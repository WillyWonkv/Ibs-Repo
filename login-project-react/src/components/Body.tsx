import { EllipsisOutlined } from "@ant-design/icons"
import "./Body.css"
import { Button, Card, Dropdown, Flex, MenuProps } from "antd"

const contentStyle:React.CSSProperties = {
}

const items: MenuProps["items"] = [
    {
        key:"adventure",
        label:"Adventure",
        className:"menuGenresButton",
        onClick: ()=> {}
    },
    {
        key:"anime",
        label:"Anime",
        className:"menuGenresButton",
        onClick: ()=> {}
    },
    {
        key:"action",
        label:"Action",
        className:"menuGenresButton",
        onClick: ()=> {}
    },
    {
        key:"crime",
        label:"Crime",
        className:"menuGenresButton",
        onClick: ()=> {}
    },
    {
        key:"documentary",
        label:"Documentary",
        className:"menuGenresButton",
        onClick: ()=> {}
    },
    {
        key:"fantasy",
        label:"Fantasy",
        className:"menuGenresButton",
        onClick: ()=> {}
    },
    {
        key:"horror",
        label:"Horror",
        className:"menuGenresButton",
        onClick: ()=> {}
    },
    {
        key:"thriller",
        label:"Thriller",
        className:"menuGenresButton",
        onClick: ()=> {}
    },
    {
        key:"international",
        label:"International",
        className:"menuGenresButton",
        onClick: ()=> {}
    },
    {
        key:"sport",
        label:"Sport",
        className:"menuGenresButton",
        onClick: ()=> {}
    },
]

export const Body = () =>{

    return (
        <div style={contentStyle}>

            <Dropdown 
                menu={{items}} 
                placement="bottomLeft" 
                trigger={["click"]}
                popupRender={(menu) => (
                    <div className="menuGenresStyle">
                        {menu}
                    </div>
                )}
                >
                <Button
                    shape="round"
                    type="text"
                    className="genresbutton"
                    icon={<EllipsisOutlined/>}
                    iconPlacement="end"
                    style={{color:"white"}}
                    >Genres</Button>
            </Dropdown>


        </div>

        
    )

}