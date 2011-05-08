
Function.prototype.bind = function( scope ) {
    var self = this;
    return function() {
        return self.apply( scope, arguments );
    }
};

if ( !window.sockso ) {
    window.sockso = {};
}

sockso.mobile = {
    page: {}
};

sockso.mobile.Player = function() {};

////////////////////////////////
//
//  sockso.mobile.Player
//
////////////////////////////////

sockso.mobile.Player.prototype = {

    audio: null,
    
    isReady: false,
    
    init: function() {
    
        $( this.onDomReady.bind(this) );
        
    },
    
    play: function( trackId ) {
    
        if ( this.isReady ) {
            this.audio.setAttribute( 'src', '/stream/' +trackId );
            this.audio.play();
        }
    
    },
    
    onDomReady: function() {
    
        this.audio = $( '<audio preload="auto" autobuffer></audio>' )
            .appendTo( 'body' )
            [ 0 ];
        
        this.isReady = true;
        
    }

};

////////////////////////////////
//
//  sockso.mobile.page.Search
//
////////////////////////////////

sockso.mobile.page.Search = function() {

    this.container = $( '#search' );

};

sockso.mobile.page.Search.prototype = {

    init: function() {}

};

////////////////////////////////
//
//  sockso.mobile.page.Home
//
////////////////////////////////

sockso.mobile.page.Home = function() {

    this.container = $( '#home' );

};

sockso.mobile.page.Home.prototype = {

    /**
     * Initialise the home page
     *
     */
    init: function() {
    }

};

////////////////////////////////
//
//  sockso.mobile.page.Playing
//
////////////////////////////////

sockso.mobile.page.Playing = function( options ) {

    this.content = $( '#playing div[data-role="content"]' );

    $.extend( this, options || {} );

};

sockso.mobile.page.Playing.prototype = {

    init: function() {
    
        $( '#playing' )
            .live( 'pageshow', this.onPageShow.bind(this) );

    },
    
    onPageShow: function() {
    
        var track = {
        
            id: 1648,
            name: 'Electric Guitar',
            
            album: {
                id: '1648',
                name: 'The Album'                
            },
            
            artist: {
                id: '1648',
                name: 'Chris Rea'
            }
        
        };
        
        this.playTrack( track );
        this.displayTrack( track );
    
    },
    
    playTrack: function( track ) {
    
//        this.player.play( track.id );
    
    },
    
    displayTrack: function( track ) {
    
        var title = $( '<h2></h2>' )
                            .html( track.name+ ' (' +track.artist.name+ ')' );
                            
        var cover = $( '<img></img>' )
                            .attr( 'src', '/file/cover/al' +track.album.id );
    
        var display = $( '<div></div>' )
                            .append( title )
                            .append( cover );

        $( '#playing div[data-role="content"]' )
            .empty()
            .append( display );
    
    }

};

////////////////////////////////
//
//  sockso.mobile.App
//
////////////////////////////////

sockso.mobile.App = function() {};

sockso.mobile.App.prototype = {

    /**
     * Start the app, initialising page objects, bind event handlers, etc...
     *
     */
    start: function() {
    
        var player = new sockso.mobile.Player();
        player.init();
        
        var pageOptions = {
            player: player
        };
 
        var pages = {
            home: new sockso.mobile.page.Home(),
            search: new sockso.mobile.page.Search(),
            playing: new sockso.mobile.page.Playing( pageOptions )
        };

        $.each( pages, function(i,page) {
            page.init();
        });
    
    }
    
};

(function() {

    var app = new sockso.mobile.App();
    app.start();

})();
