define(function(require, exports, module) {

	require('umeditor.config');
	require('umeditor');
	
	umEditor = UM.getEditor('content', {
		imageUrl: window.app.base + "/aj_um_upload.json",
    	imagePath: "$base",
        toolbar: ["undo", "redo", "|", "bold", "italic", "underline", "|", "justifyleft", "justifycenter",
            "justifyright", "|", "link", "unlink", "|", "insertorderedlist", "insertunorderedlist", 
			"|", "emotion", "image", "removeformat", "fullscreen"],
        autoClearinitialContent: true,
        wordCount: true,
        maximumWords: 2000,
        initialFrameWidth: '100%',
        initialFrameHeight: 300
    });
	
});