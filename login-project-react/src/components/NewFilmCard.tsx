import { InboxOutlined, PlusCircleOutlined } from "@ant-design/icons";
import { Button, Card, Flex, Input, InputNumber, Select } from "antd";
import Dragger from "antd/es/upload/Dragger";
import { Film, Genre } from "../service/FilmsService";

interface NewFilmCardProps{

    isAdding: boolean;
    genres: Genre[];
    newFilmData: Partial<Film>;

    setIsAdding: (v: boolean) => void;
    setNewFilmData: React.Dispatch<React.SetStateAction<Partial<Film>>>;    

    onCreate: () => void;
    fetchGenres: () => void;

}


export const NewFilmCard = ({
    isAdding,
    genres,
    newFilmData,
    setIsAdding,
    setNewFilmData,
    onCreate,
    fetchGenres,
}:NewFilmCardProps) => {

    return(
        <Card 
            key="new-film"
            style={{
                position: "relative",
                width: 260,
                height: "100%",
                display: "flex",
                flexDirection: "column",
                padding: "16px",
                }}
        >
            {isAdding ? (
                <div style={{width:"100%"}}>   
                    
                    <span style={{fontSize:"16px", fontWeight:"bold"}}>New Film</span>
                    <br/>
                    <br/>
                    
                    <span style={{fontSize:"12px"}}>Cover:</span>

                    <Dragger
                        beforeUpload={(file) => {
                            setNewFilmData(prev => ({...prev, cover: file}))
                            return false;
                        }}
                        multiple={false}
                        accept=".jpeg,.jpg"
                        showUploadList={{showRemoveIcon:true}}
                        onRemove={() => setNewFilmData(prev => ({...prev, cover:undefined}))}
                    >
                        
                        <p className="ant-upload-drag-icon">
                        <InboxOutlined />
                        </p>
                        <p className="ant-upload-text">Click or drag a file</p>
                    
                    </Dragger>


                    <span style={{fontSize:"12px"}}>Title:</span>
                    <Input 
                        value={newFilmData.title || ""}
                        onChange={e => setNewFilmData(prev => ({...prev, title: e.target.value}))}
                        placeholder="Title"
                        style={{
                            marginBottom:"8px"
                        }}
                    />
                    <span style={{fontSize:"12px"}}>Description:</span>
                    <Input.TextArea 
                        value={newFilmData.description || ""}
                        onChange={e => setNewFilmData(prev => ({...prev, description: e.target.value}))}
                        placeholder="Description"
                        style={{
                            marginBottom:"8px",
                            width:"100%",
                            height:"150px",
                            resize:"none",
                            overflowY:"auto"
                        }}
                    />
                    <span style={{fontSize:"12px"}}>Duration (min):</span>
                    <InputNumber
                        value={newFilmData.duration || 0}
                        onChange={(value) => setNewFilmData(prev => ({...prev, duration: value || 0}))}
                        placeholder="Duration"
                        style={{marginBottom:"8px", width:"100%"}}
                    />

                    <span style={{fontSize:"12px"}}>Genres</span>
                    <Select
                        mode="multiple"
                        style={{ width: '100%', marginBottom:"8px" }}
                        placeholder="Select genres"
                        value={newFilmData.genres?.map(g => g.id) || []}
                        onChange={(selectedIds: number[]) =>{
                            const selectedGenres = genres.filter(g => selectedIds.includes(g.id));
                            setNewFilmData(prev => ({...prev, genres: selectedGenres}))
                        }}
                    >
                        {genres.map(genre => (
                            <Select.Option 
                                key={genre.id}
                                value={genre.id}
                            >
                                {genre.name}
                            </Select.Option>
                        ))}
                    </Select>
                    <Flex gap={8}>
                        <Button type="primary" onClick={onCreate}>Create</Button>
                        <Button onClick={() => setIsAdding(false)}>Cancel</Button>
                    </Flex>
                </div>
            ) : (
                <Button 
                    variant="link" 
                    color="default" 
                    onClick={() => {
                        setIsAdding(true)
                        fetchGenres()
                    }}
                    style={{position:"absolute",top:"50%",left:"50%",transform:"translate(-50%,-50%)"}}
                >
                    <PlusCircleOutlined style={{fontSize:"30px"}} />
                </Button>

            )}
        </Card>


    )

}