if ( !Processing )
    console.error("Can not find dependence `Processing`")


function LogicMatrix(id) {
    // response to click
    this.interactive = false
    this.h = 0
    this.w = 0
    this.mat = []
    this.cols = 0
    this.rows = 0
    this.rh = 0
    this.rw = 0
    this.cid = id
    this.mouseClickedCallback = function(i,j){}

    // append a canvas to the container
    var c = $('<canvas id="lm-canvas-'+id+'">')
    c.appendTo($("#"+id))

    // Processing instance
    this.pjs = new Processing(c.get(0))
    this.pjs.ct = this

    /******************* set up pjs functions*********************/
    this.pjs.setup = function() {
        this.size(this.ct.w, this.ct.h)
        this.background(237)
        this.noLoop()
    }

    this.pjs.draw = function() {
        var that = this.ct
        for(i=0;i<that.rows;i++) {
            for(j=0;j<that.cols;j++) {
                if ( that.mat[i][j] == 0 )
                    this.fill(250)
                else
                    this.fill(37)
                this.rect(j*that.rw,i*that.rh,that.rw,that.rh)
            }
        }
        this.line(0,this.height-1,this.width-1,this.height-1)
        this.line(this.width-1,this.height-1,this.width-1,0)
    }

    this.pjs.mouseClicked = function() {
        if ( !this.ct.interactive )
            return
        var pi,pj
        pj = Math.floor(this.mouseX / this.ct.rw)
        pi = Math.floor(this.mouseY / this.ct.rh)

        this.ct.mat[pi][pj] = Math.round(Math.abs(1-this.ct.mat[pi][pj]))
        this.redraw()

        this.ct.mouseClickedCallback(pi,pj)
    }
    /**********END**********/

    this.loadMat = function(m) {
        this.mat = m
        this.cols = this.mat[0].length
        this.rows = this.mat.length
        this.rh = (this.h)/this.rows
        this.rw = (this.w)/this.cols

    }


    this.getMat = function() {
        return this.mat
    }


    this.init = function() {
        var ct = $('#'+this.cid)
        this.w = ct.width()
        this.h = ct.height()

        this.pjs.setup()
    }

    this.update = function() {
        this.pjs.redraw()
    }

    // setup pjs
    this.init()
}
